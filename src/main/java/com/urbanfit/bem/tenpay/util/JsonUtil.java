package com.urbanfit.bem.tenpay.util;

import com.urbanfit.bem.util.JsonUtils;
import net.sf.json.JSONObject;

public class JsonUtil {

    public static String getJsonValue(String rescontent, String key) {
        JSONObject jsonObject;
        String v = null;
        try {
            jsonObject = JsonUtils.getJsonObject4JavaPOJO(rescontent);
            v = jsonObject.getString(key);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return v;
    }
}
