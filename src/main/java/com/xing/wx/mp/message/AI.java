package com.xing.wx.mp.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import me.chanjar.weixin.common.api.WxConsts;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : xingshukui
 * @email : xingshukui@890media.com
 * @date : 2018/11/6 12:09 PM
 * @desc :
 */
public class AI {
    private static final String url = "http://www.tuling123.com/openapi/api/v2";
    private static final String apiKey = "cc4916bfe1854baf8afdf0ed2908f83f";
    private static CloseableHttpClient httpClient = HttpClients.createDefault();

    private static AI ai = new AI();

    private AI() {
    }

    public static AI instance() {
        if (ai == null) {
            ai = new AI();
        }
        return ai;
    }


    public String aiMsg(String context) {
        String result = "";
        Map<String, Object> paramMap = new HashMap<String, Object>();

        Map<String, Object> perception = new HashMap<String, Object>();
        Map<String, String> inputText = new HashMap<String, String>();
        inputText.put("text", context);
        perception.put("inputText", inputText);
        Map<String, String> userInfo = new HashMap<String, String>();
        userInfo.put("apiKey", apiKey);
        userInfo.put("userId", "341212");


        paramMap.put("perception", perception);
        paramMap.put("userInfo", userInfo);
        try {
            result = doPost(paramMap);
            JSONObject obj = JSON.parseObject(result);
            JSONObject res = JSONObject.parseObject(obj.getString("intent"));
            if (res.getString("code").startsWith("1")) {
                JSONArray r = JSONArray.parseArray(obj.getString("results"));
                JSONObject first = JSONObject.parseObject(JSONObject.toJSONString(r.get(0)));
                if (first.getString("resultType").equalsIgnoreCase(WxConsts.XmlMsgType.TEXT)) {
                    JSONObject o = JSONObject.parseObject(first.getString("values"));
                    result = o.getString("text");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private String doPost(Map<String, Object> paramMap) throws IOException {
        String paramStr = JSON.toJSONString(paramMap);
        HttpPost httpPost;
        StringEntity params = new StringEntity(paramStr, Consts.UTF_8);
        httpPost = new HttpPost(url);
        httpPost.setEntity(params);
        httpPost.setHeader("Content-type", "application/json; charset=utf-8");
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36");
        CloseableHttpResponse response = httpClient.execute(httpPost);
        return EntityUtils.toString(response.getEntity(), "UTF-8");
    }


}
