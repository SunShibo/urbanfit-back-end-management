package com.urbanfit.bem.service;

import com.urbanfit.bem.cfg.pop.Constant;
import com.urbanfit.bem.cfg.pop.SystemConfig;
import com.urbanfit.bem.dao.*;
import com.urbanfit.bem.entity.*;
import com.urbanfit.bem.pay.*;
import com.urbanfit.bem.query.PageObject;
import com.urbanfit.bem.query.PageObjectUtil;
import com.urbanfit.bem.query.QueryInfo;
import com.urbanfit.bem.tenpay.util.JsonUtil;
import com.urbanfit.bem.util.*;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Resource
    private CourseStoreDao courseStoreDao;
    @Resource
    private StoreDao storeDao;
    @Resource
    private CourseSizeDetailDao courseSizeDetailDao;
    @Resource
    private CourseSizeDao courseSizeDao;

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

    public String addClientOrderMaster(String params, ClientInfo clientInfo, HttpServletRequest request,
                                       HttpServletResponse response){
        if(clientInfo == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_CLIENT_NO_LOGIN, "没有登录账号", "").toString();
        }
        if(StringUtils.isEmpty(params)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        OrderMaster order = null;
        try{
            order = (OrderMaster)JsonUtils.getObject4JsonString(params, OrderMaster.class);
            order.setClientId(clientInfo.getClientId());
        }catch (Exception e){
            e.printStackTrace();
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        if(order.getCourseId() == null || StringUtils.isEmpty(order.getChildrenName()) || order.getStoreId() == null
                || StringUtils.isEmpty(order.getClientMobile()) || order.getPayment() == null
                || order.getDetailId() == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        // 查询课程是否存在
        Course course = courseDao.queryUpCourseByCourseId(order.getCourseId());
        if(course == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "课程不存在或已经下架", "").toString();
        }
        CourseSizeDetail courseSizeDetail = courseSizeDetailDao.queryCourseSizeDetailById(order.getDetailId());
        if(courseSizeDetail == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "课程规格不存在", "").toString();
        }
        if(courseSizeDetail.getIsSale() == CourseSizeDetail.IS_SALE_NO){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "课程规格不可售，请重新选择",
                    "").toString();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("courseId", order.getCourseId());
        map.put("lstSizeId", courseSizeDetail.getSizeDetail().split(","));
        List<CourseSize> lstCourseSize = courseSizeDao.queryCourseSizeInfo(map);
        if(CollectionUtils.isEmpty(lstCourseSize)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "课程规格不存在", "").toString();
        }
        // 查询课程是否绑定俱乐部
        Map<String, Object> courseStoreMap = new HashMap<String, Object>();
        courseStoreMap.put("courseId", order.getCourseId());
        courseStoreMap.put("storeId", order.getStoreId());
        CourseStore courseStore = courseStoreDao.queryCourseStoreByMap(courseStoreMap);
        if(courseStore == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "课程没有关联此俱乐部", "").toString();
        }
        // 查询俱乐部是否存在
        Store store = storeDao.queryStoreById(order.getStoreId());
        if(store == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "俱乐部不存在", "").toString();
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
        OrderMaster orderMaster = addOrderMasterDetail(order, coupon, course, orderNum, courseSizeDetail);
        if (order.getPayment() == OrderMaster.PAYMENT_ALIPAY) {  // 支付宝支付

            String alipayCallbackUrl = SystemConfig.getString("project_base_url") + SystemConfig.
                    getString("alipay_order_callback_url");
            String alipayReturnUrl = SystemConfig.getString("project_base_url") + SystemConfig.
                    getString("alipay_order_return_url");
            String alipayResult = WebAlipayUtil.submitClientAlipay("众力飞特", "众力飞特课程支付",
                    orderNum, orderMaster.getPayPrice(), alipayCallbackUrl, alipayReturnUrl);
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "调用支付宝", alipayResult).toString();
        }else if(order.getPayment() == OrderMaster.PAYMENT_WECHAT) {  // 微信支付

            String tenpayCallbackUrl = SystemConfig.getString("project_base_url") +  SystemConfig.
                    getString("wxpay_order_callback_url");
            return WebWeChatPayUtil.submitPrepayToWeChat(request, response, orderNum, "众力飞特课程支付",
                    (int) (orderMaster.getPayPrice() * 100), tenpayCallbackUrl, "NATIVE", order.getCourseId().
                    toString()).toString();
        }
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
    }

    /**
     * 订单再支付
     */
    public String payOrderMasterAgain(HttpServletRequest request, HttpServletResponse response, String params,
                                      ClientInfo clientInfo){
        if(clientInfo == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "没有登录账号", "").toString();
        }
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
            String alipayReturnUrl = SystemConfig.getString("project_base_url") + SystemConfig.
                    getString("alipay_order_return_url");
            String alipayResult = WapAlipayUtil.submitClientAlipay("众力飞特课程支付",
                    orderMaster.getOrderNum(), orderMaster.getPayPrice(), alipayCallbackUrl, alipayReturnUrl);
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "调用支付宝", alipayResult).toString();
        }else if(order.getPayment() == OrderMaster.PAYMENT_WECHAT) {  // 微信支付

            String tenpayCallbackUrl = SystemConfig.getString("project_base_url") +  SystemConfig.
                    getString("wxpay_order_callback_url");
            return WebWeChatPayUtil.submitPrepayToWeChat(request, response, order.getOrderNum(), "众力飞特课程支付",
                    (int) (orderMaster.getPayPrice() * 100), tenpayCallbackUrl, "NATIVE", orderMaster.getCourseId().
                            toString()).toString();
        }
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
    }

    public String payWapOrderMasterAgain(String params, HttpServletRequest request, HttpServletResponse response){
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
            String alipayReturnUrl = SystemConfig.getString("wap_project_base_url") + SystemConfig.
                    getString("wap_alipay_order_return_url");
            String alipayResult = WapAlipayUtil.submitClientAlipay("众力飞特课程支付",
                    orderMaster.getOrderNum(), orderMaster.getPayPrice(), alipayCallbackUrl, alipayReturnUrl);
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "调用支付宝", alipayResult).toString();
        }else if(order.getPayment() == OrderMaster.PAYMENT_WECHAT) {  // 微信支付

            String tenpayCallbackUrl = SystemConfig.getString("project_base_url") +  SystemConfig.
                    getString("wxpay_order_callback_url");
            String tenpayReturnUrl = SystemConfig.getString("wap_project_base_url") + SystemConfig.
                    getString("wxpay_order_return_url");
            return WapWechatPayUtil.submitPrepayToWeChat(request,order.getOrderNum(), "众力飞特课程支付",
                    (int) (orderMaster.getPayPrice() * 100), tenpayCallbackUrl, "MWEB", tenpayReturnUrl).toString();
        }
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
    }


    /**
     * 支付成功回调函数
     */
    public void payOrderMasterSuccess(String orderNum, Integer payment){
        OrderMaster orderMaster = orderMasterDao.queryOrderMaterDetail(orderNum);
        System.out.println("service========支付方法====");
        if(orderMaster != null && orderMaster.getStatus() == OrderMaster.STATUS_WAITING_PAY){
            System.out.println("=========真正支付==========");
            // 修改支付状态、支付时间、支付类型
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("orderNum", orderNum);
            map.put("status", OrderMaster.STATUS_PAYED);
            map.put("payTime", new Date());
            map.put("payment", payment);
            orderMasterDao.updateOrderMaster(map);
        }
    }

    private OrderMaster addOrderMasterDetail(OrderMaster order, Coupon coupon, Course course, String orderNum,
                                             CourseSizeDetail courseSizeDetail){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setClientId(order.getClientId());
        orderMaster.setChildrenName(order.getChildrenName());
        orderMaster.setClientMobile(order.getClientMobile());
        orderMaster.setOrderNum(orderNum);
        orderMaster.setCourseId(order.getCourseId());
        orderMaster.setPrice(courseSizeDetail.getSizePrice());
        orderMaster.setCourseName(course.getCourseName());
        orderMaster.setCourseDistrict(order.getCourseDistrict());
        orderMaster.setDetailId(courseSizeDetail.getDetailId());     // 课程规格信息
        orderMaster.setStoreId(order.getStoreId());               // 课程俱乐部
        // 查询商品规格信息
        Map<String, Object> courseSizeMap = new HashMap<String, Object>();
        courseSizeMap.put("lstSizeId", courseSizeDetail.getSizeDetail().split(","));
        List<CourseSize> lstCourseSize = courseSizeDao.queryCourseSizeInfo(courseSizeMap);
        List<String> lstSizeName = new ArrayList<String>();
        for (CourseSize courseSize : lstCourseSize){
            lstSizeName.add(courseSize.getSizeName());
        }
        orderMaster.setCourseSize(ArrayUtils.join(lstCourseSize.toArray(), "-"));
        double payPrice = courseSizeDetail.getSizePrice();
        orderMaster.setPayPrice(payPrice);
        orderMaster.setRemarks(order.getRemarks());
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

    public String queryPayOrderMasterDetail(String orderNum){
        if(StringUtils.isEmpty(orderNum)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        OrderMaster orderMaster = orderMasterDao.queryOrderMasterByOrderNum(orderNum);
        if(orderMaster == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "订单不存在", "").toString();
        }
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询成功", orderMaster.getStatus()
                + "").toString();
    }

    public String queryClientOrderMasterDetail(String orderNum){
        OrderMaster orderMaster = orderMasterDao.queryOrderMasterByOrderNum(orderNum);
        if(orderMaster == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "订单不存在", "").toString();
        }
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询成功", JsonUtils.
                getJsonString4JavaPOJO(orderMaster, DateUtils.LONG_DATE_PATTERN)).toString();
    }

    public String queryOrderMasterList(Integer clientId, QueryInfo queryInfo){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("clientId", clientId);
        if(queryInfo != null){
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());
        }
        PageObjectUtil<OrderMaster> page = new PageObjectUtil<OrderMaster>();
        PageObject pager =  page.savePageObject(orderMasterDao.queryClientOrderMasterCount(map),
                orderMasterDao.queryClientOrderMasterList(map), queryInfo);
        List<OrderMaster> lstOrder = pager.getDatas();
        JSONObject jo = new JSONObject();
        if(lstOrder != null && lstOrder.size() != 0){
            jo.put("totalRecord", pager.getTotalRecord());
            jo.put("lstOrder", JsonUtils.getJsonString4JavaListDate(lstOrder, DateUtils.LONG_DATE_PATTERN));
        }else{
            jo.put("totalRecord", pager.getTotalRecord());
            jo.put("lstOrder", "");
        }
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询成功", jo.toString()).toString();
    }

    public String wapAddOrderMaster(HttpServletRequest request, HttpServletResponse response, String params,
                                    Integer clientId){
        if(StringUtils.isEmpty(params) || clientId == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        ClientInfo clientInfo = clientInfoDao.queryClientById(clientId);
        if(clientInfo == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "用户不存在", "").toString();
        }
        OrderMaster order = null;
        try{
            order = (OrderMaster)JsonUtils.getObject4JsonString(params, OrderMaster.class);
            order.setClientId(clientInfo.getClientId());
        }catch (Exception e){
            e.printStackTrace();
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        if(order.getCourseId() == null || StringUtils.isEmpty(order.getChildrenName()) || order.getStoreId() == null
                || StringUtils.isEmpty(order.getClientMobile()) || order.getPayment() == null
                || order.getDetailId() == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        // 查询课程是否存在
        Course course = courseDao.queryUpCourseByCourseId(order.getCourseId());
        if(course == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "课程不存在或已经下架", "").toString();
        }
        CourseSizeDetail courseSizeDetail = courseSizeDetailDao.queryCourseSizeDetailById(order.getDetailId());
        if(courseSizeDetail == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "课程规格不存在", "").toString();
        }
        if(courseSizeDetail.getIsSale() == CourseSizeDetail.IS_SALE_NO){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "课程规格不可售，请重新选择",
                    "").toString();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("courseId", order.getCourseId());
        map.put("lstSizeId", courseSizeDetail.getSizeDetail().split(","));
        List<CourseSize> lstCourseSize = courseSizeDao.queryCourseSizeInfo(map);
        if(CollectionUtils.isEmpty(lstCourseSize)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "课程规格不存在", "").toString();
        }
        // 查询课程是否绑定俱乐部
        Map<String, Object> courseStoreMap = new HashMap<String, Object>();
        courseStoreMap.put("courseId", order.getCourseId());
        courseStoreMap.put("storeId", order.getOrderId());
        CourseStore courseStore = courseStoreDao.queryCourseStoreByMap(courseStoreMap);
        if(courseStore == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "课程没有关联此俱乐部", "").toString();
        }
        // 查询俱乐部是否存在
        Store store = storeDao.queryStoreById(order.getStoreId());
        if(store == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "俱乐部不存在", "").toString();
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
        OrderMaster orderMaster = addOrderMasterDetail(order, coupon, course, orderNum, courseSizeDetail);
        if (order.getPayment() == OrderMaster.PAYMENT_ALIPAY) {  // 支付宝支付

            String alipayCallbackUrl = SystemConfig.getString("project_base_url") + SystemConfig.
                    getString("alipay_order_callback_url");
            String alipayReturnUrl = SystemConfig.getString("wap_project_base_url") + SystemConfig.
                    getString("wap_alipay_order_return_url");
            String alipayResult = WapAlipayUtil.submitClientAlipay("众力飞特课程支付",
                    orderMaster.getOrderNum(), orderMaster.getPayPrice(), alipayCallbackUrl, alipayReturnUrl);
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "调用支付宝", alipayResult).toString();
        }else if(order.getPayment() == OrderMaster.PAYMENT_WECHAT) {  // 微信支付

            String tenpayCallbackUrl = SystemConfig.getString("project_base_url") +  SystemConfig.
                    getString("wxpay_order_callback_url");
            return WebWeChatPayUtil.submitPrepayToWeChat(request, response, orderNum, "众力飞特课程支付",
                    (int) (orderMaster.getPayPrice() * 100), tenpayCallbackUrl, "NATIVE", order.getCourseId().
                            toString()).toString();
        }
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
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
