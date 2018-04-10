package com.urbanfit.bem.service;

import com.urbanfit.bem.cfg.pop.Constant;
import com.urbanfit.bem.cfg.pop.SystemConfig;
import com.urbanfit.bem.dao.ClientInfoDao;
import com.urbanfit.bem.dao.CouponDao;
import com.urbanfit.bem.dao.CourseDao;
import com.urbanfit.bem.dao.OrderMasterDao;
import com.urbanfit.bem.entity.ClientInfo;
import com.urbanfit.bem.entity.Coupon;
import com.urbanfit.bem.entity.Course;
import com.urbanfit.bem.entity.OrderMaster;
import com.urbanfit.bem.pay.AlipayUtil;
import com.urbanfit.bem.pay.WeChatPayUtil;
import com.urbanfit.bem.query.PageObject;
import com.urbanfit.bem.query.PageObjectUtil;
import com.urbanfit.bem.query.QueryInfo;
import com.urbanfit.bem.util.DateUtils;
import com.urbanfit.bem.util.JsonUtils;
import com.urbanfit.bem.util.RandomUtils;
import com.urbanfit.bem.util.StringUtils;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/9.
 */
@Service("orderMasterService")
public class OrderMasterService {
    @Resource
    private OrderMasterDao orderMasterDao;
    @Resource
    private ClientInfoDao clientInfoDao;
    @Resource
    private CourseDao courseDao;
    @Resource
    private CouponDao couponDao;

    public OrderMaster queryOderMaterDetail(String orderNum){
        return orderMasterDao.queryOrderMaterDetail(orderNum);
    }

    public String updateOrderMasterStatus(String orderNum){
        if(StringUtils.isEmpty(orderNum)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        OrderMaster orderMaster = orderMasterDao.queryOrderMasterByOrderNum(orderNum);
        if(orderMaster == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "订单不存在", "").toString();
        }
        if(orderMaster.getStatus() == OrderMaster.STATUS_WAITING_PAY){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "订单待支付", "").toString();
        }
        if(orderMaster.getStatus() == OrderMaster.STATUS_REFUND){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "订单状态已经为已退款", "").toString();
        }
        orderMasterDao.updateOrderMasterStatus(orderNum);
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "修改成功", "").toString();
    }

    public PageObject<OrderMaster> queryClientOrderMaster(Integer clientId, String orderNum, Integer status,
                                                          QueryInfo queryInfo){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("clientId", clientId);
        if(!StringUtils.isEmpty(orderNum)){
            map.put("orderNum", orderNum);
        }
        if(status != null){
            map.put("status", status);
        }
        if(queryInfo != null){
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());
        }
        PageObjectUtil<OrderMaster> page = new PageObjectUtil<OrderMaster>();
        return page.savePageObject(orderMasterDao.queryClientOrderMasterCount(map),
                orderMasterDao.queryClientOrderMasterList(map), queryInfo);
    }

    public String queryClientOrderMasterDetail(Integer clientId, String orderNum){
        if(clientId == null || StringUtils.isEmpty(orderNum)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("clientId", clientId);
        map.put("orderNum", orderNum);
        OrderMaster orderMaster = orderMasterDao.queryClientOrderMaterDetail(map);
        if(orderMaster == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "订单不存在", "").toString();
        }
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询成功", JsonUtils.
                getJsonString4JavaPOJO(orderMaster, DateUtils.LONG_DATE_PATTERN)).toString();
    }

    public String addClientOrderMaster(String params, HttpServletRequest request, HttpServletResponse response){
        if(StringUtils.isEmpty(params)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        OrderMaster order = null;
        try{
            order = (OrderMaster)JsonUtils.getObject4JsonString(params, OrderMaster.class);
        }catch (Exception e){
            e.printStackTrace();
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        if(order.getClientId() == null || order.getCourseId() == null || StringUtils.isEmpty(order.getChildrenName())
                || StringUtils.isEmpty(order.getClientMobile()) || StringUtils.isEmpty(order.getCourseDistrict())
                || order.getPayment() == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        // 查询客户是否存在
        ClientInfo clientInfo = clientInfoDao.queryClientById(order.getClientId());
        if(clientInfo == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "客户不存在", "").toString();
        }
        // 查询课程是否存在
        Course course = courseDao.queryUpCourseByCourseId(order.getCourseId());
        if(course == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "课程不存在或已经下架", "").toString();
        }
        Coupon coupon = null;
        // 如果优惠码不为空，查询优惠码是否存在
        if(!StringUtils.isEmpty(order.getCouponNum())) {
            coupon = couponDao.queryCouponByCouponNum(order.getCouponNum());
            if (coupon == null) {
                return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "优惠码不存在货已过期", "").toString();
            }
        }
        //生成主订单编号
        String orderNum  = System.currentTimeMillis() + "" + RandomUtils.getRandomNumber(6);
        OrderMaster orderMaster = addOrderMasterDetail(order, coupon, course, orderNum);
        if (order.getPayment() == OrderMaster.PAYMENT_ALIPAY) {  // 支付宝支付

            String alipayCallbackUrl = SystemConfig.getString("project_base_url") + SystemConfig.
                    getString("alipay_order_callback_url");
            String alipayResult = AlipayUtil.submitClientlipay("众力飞特", "众力飞特课程支付",
                    orderNum, orderMaster.getPayPrice(), alipayCallbackUrl);
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "调用支付宝", alipayResult).toString();
        }else if(order.getPayment() == OrderMaster.PAYMENT_WECHAT) {  // 微信支付

            String tenpayCallbackUrl =SystemConfig.getString("project_base_url") +  SystemConfig.
                    getString("wxpay_order_callback_url");
            return WeChatPayUtil.submitPrepayToWeChat(request, response, orderNum, "众力飞特课程支付",
                    (int) (orderMaster.getPayPrice() * 100), "众力飞特课程支付", "", tenpayCallbackUrl).toString();
        }
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
    }

    /**
     * 订单再支付
     */
    public String payOrderMasterAgain(HttpServletRequest request, HttpServletResponse response, String params){
        if(StringUtils.isEmpty(params)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        OrderMaster order = null;
        try {
            order = (OrderMaster)JsonUtils.getObject4JsonString(params, OrderMaster.class);
        }catch (Exception e){
            e.printStackTrace();
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        if(order.getPayment() == null || StringUtils.isEmpty(order.getOrderNum())){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        OrderMaster orderMaster = orderMasterDao.queryOrderMaterDetail(order.getOrderNum());
        if(orderMaster == null || (orderMaster != null && (orderMaster.getStatus() == OrderMaster.STATUS_PAYED
                || orderMaster.getStatus() == OrderMaster.STATUS_REFUND))){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "订单不存在或已经支付", "").toString();
        }

        if (order.getPayment() == OrderMaster.PAYMENT_ALIPAY) {  // 支付宝支付

            String alipayCallbackUrl = SystemConfig.getString("project_base_url") + SystemConfig.
                    getString("alipay_order_callback_url");
            String alipayResult = AlipayUtil.submitClientlipay("众力飞特", "众力飞特课程支付",
                    orderMaster.getOrderNum(), orderMaster.getPrice(), alipayCallbackUrl);
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "调用支付宝", alipayResult).toString();
        }else if(order.getPayment() == OrderMaster.PAYMENT_WECHAT) {  // 微信支付

            String tenpayCallbackUrl =SystemConfig.getString("project_base_url") +  SystemConfig.
                    getString("wxpay_order_callback_url");
            return WeChatPayUtil.submitPrepayToWeChat(request, response, order.getOrderNum(), "众力飞特课程支付",
                    (int) (orderMaster.getPayPrice() * 100), "众力飞特课程支付", "", tenpayCallbackUrl).toString();
        }
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
    }

    /**
     * 支付成功回调函数
     */
    public void payOrderMasterSuccess(String orderNum, Integer payment){
        OrderMaster orderMaster = orderMasterDao.queryOrderMaterDetail(orderNum);
        if(orderMaster != null && orderMaster.getStatus() == OrderMaster.STATUS_WAITING_PAY){
            // 修改支付状态、支付时间、支付类型
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("orderNum", orderNum);
            map.put("status", OrderMaster.STATUS_PAYED);
            map.put("payTime", new Date());
            map.put("payment", payment);
            orderMasterDao.updateOrderMaster(map);
        }
    }

    private OrderMaster addOrderMasterDetail(OrderMaster order, Coupon coupon, Course course, String orderNum){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setClientId(order.getClientId());
        orderMaster.setChildrenName(order.getChildrenName());
        orderMaster.setClientMobile(order.getClientMobile());
        orderMaster.setOrderNum(orderNum);
        orderMaster.setCourseId(order.getCourseId());
        orderMaster.setPrice(course.getCoursePrice());
        orderMaster.setCourseName(course.getCourseName());
        orderMaster.setCourseDistrict(order.getCourseDistrict());
        double payPrice = course.getCoursePrice();
        orderMaster.setPayPrice(payPrice);
        // 获取支付价格，如果没有使用优惠码，支付价格为课程价格，如果使用优惠码，支付价格为课程价格-优惠码价格
        if(coupon != null){
            payPrice = payPrice - (payPrice * coupon.getPercent() / (double)100);
            orderMaster.setPayPrice(payPrice);
            orderMaster.setIsUseCoupon(OrderMaster.USE_COUPON);
            orderMaster.setCouponId(coupon.getCouponId());
            orderMaster.setCouponNum(coupon.getCouponNum());
            orderMaster.setCouponPercent(coupon.getPercent());
            orderMaster.setCouponPrice(payPrice * coupon.getPercent() / (double)100);
        }
        orderMaster.setPayment(order.getPayment());
        orderMaster.setStatus(OrderMaster.STATUS_WAITING_PAY);
        orderMaster.setCreateTime(new Date());
        // 设置订单自动取消时间  半个小时之后取消
        orderMaster.setSystemCancleTime(getSystemCancleTime());
        // 添加订单
        orderMasterDao.addOrderMaster(orderMaster);
        return orderMaster;
    }


    private Date getSystemCancleTime() {
        long curren = System.currentTimeMillis();
        curren += 30 * 60 * 1000;
        Date systemCancleTime = new Date(curren);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(systemCancleTime));
        return systemCancleTime;
    }
}
