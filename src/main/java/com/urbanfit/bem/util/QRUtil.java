package com.urbanfit.bem.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.urbanfit.bem.cfg.pop.SystemConfig;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.Date;
import java.util.Hashtable;
import java.util.Random;

/**
 * Created by Administrator on 2018/4/21.
 */
public class QRUtil {
    public static void create(String text, OutputStream stream, Integer size) throws Exception {

        int width = 100 + (10 * size);
        int height = 100 + (10 * size);
        // 二维码的图片格式
        String format = "jpg";
        Hashtable hints = new Hashtable();
        // 内容所使用编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);

        int[] rec = bitMatrix.getEnclosingRectangle();
        int resWidth = rec[2] + 1;
        int resHeight = rec[3] + 1;
        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
        resMatrix.clear();
        for (int i = 0; i < resWidth; i++) {
            for (int j = 0; j < resHeight; j++) {
                if (bitMatrix.get(i + rec[0], j + rec[1])) {
                    resMatrix.set(i, j);
                }
            }
        }
        MatrixToImageWriter.writeToStream(resMatrix, format, stream);
    }


    public static String generatedQRCode(String url, String dirPathUrl, String visitePathUrl) throws Exception{
        int width = 300;
        int height = 300;
        // 二维码的图片格式
        String format = "jpg";
        Hashtable hints = new Hashtable();
        // 内容所使用编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(url,
                BarcodeFormat.QR_CODE, width, height, hints);

        String basePath = SystemConfig.getString("client_upload_base");
        String dirPath = SystemConfig.getString(dirPathUrl);

        String imageRoot = SystemConfig.getString("img_file_root");
        String vistePath = SystemConfig.getString(visitePathUrl);

        String yyyyMMdd = DateUtils.formatDate(
                DateUtils.DATE_PATTERN_PLAIN, new Date());

        String yyyyMMddhhmmss = DateUtils.formatDate(
                DateUtils.LONG_DATE_PATTERN_PLAIN, new Date());
        String imageUrl = "";
        String imageName = yyyyMMddhhmmss + "_" + new Random().nextInt(1000) + "." + format;

        // 设置文件存放位置
        String path = basePath + dirPath + yyyyMMdd;
        File filepath = new File(path);
        if (!filepath.exists()) {
            filepath.mkdir();
        }
        // 生成二维码
        File outputFile = new File(path + "/" + imageName);
        if(bitMatrix == null){
            System.out.println(true);
        }
        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
        imageUrl = imageRoot + vistePath + yyyyMMdd + "/" + imageName;
        System.out.println(imageUrl);
        return imageUrl;
    }

    public static void main(String[] args) throws Exception{
        generatedQRCode("weixin://wxpay/bizpayurl?pr=w57V1w2", "wechat_pay_qr_url", "wechat_pay_qr_visit_url");
    }
}
