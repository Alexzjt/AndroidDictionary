package com.translate;

/**
 * Created by zhangjingtao on 2016/9/30.
 */

import com.util.MD5Util;
import com.util.Config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 调用百度翻译API，重写代码以适配Android环境
 */
public class BaiduTranslateDemo {
    private static final BaiduTranslateDemo baiduTranslateDemo = new BaiduTranslateDemo();

    private static final String UTF8 = "utf-8";
    private static final String requestMethod="POST";
    //申请者开发者id
    private static final String appId = Config.APPID;

    //token
    private static final String token = Config.TOKEN;

    private static final String url = Config.BAIDU;

    //随机数，用于生成md5值
    private static final Random random = new Random();

    public String translate(String q, String from, String to) throws Exception {
        //用于md5加密
        int salt = random.nextInt(10000);

        // 对appId+源文+随机数+token计算md5值
        StringBuilder md5String = new StringBuilder();
        md5String.append(appId).append(q).append(salt).append(token);
        //String md5 = DigestUtils.md5Hex(md5String.toString());
        //String md5= Md5Crypt.md5Crypt(md5String.toString().getBytes());
        String md5 = MD5Util.MD5(md5String.toString());
        //使用Post方式，组装参数
        StringBuffer sb = new StringBuffer(url);
        sb.append("?q=").append(q).append("&from=").append(from).append("&to=").append(to)
                .append("&appid=").append(appId).append("&salt=").append(String.valueOf(salt))
                .append("&sign=").append(md5);
        URL url_obj=new URL(sb.toString());
        HttpURLConnection connection=(HttpURLConnection)url_obj.openConnection();
        connection.setRequestMethod(requestMethod);
        String text=null;
        int status=connection.getResponseCode();
        if(status==200){
            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(connection.getInputStream(),UTF8));
            StringBuilder result = new StringBuilder();
            String str = null;
            while ((str = reader.readLine()) != null) {
                result.append(str).append("\n");
            }

            //转化为json对象
            JSONObject resultJson = new JSONObject(result.toString());
            try {
                String error_code = resultJson.getString("error_code");
                if (error_code != null) {
                    System.out.println("出错代码:" + error_code);
                    System.out.println("出错信息:" + resultJson.getString("error_msg"));
                    return null;
                }
            } catch (Exception e) {}

            //获取返回翻译结果
            JSONArray array = (JSONArray) resultJson.get("trans_result");
            JSONObject dst = (JSONObject) array.get(0);
            text = dst.getString("dst");
            text = URLDecoder.decode(text, UTF8);
        }

        return text;
    }

    public static String translateToEn(String q) throws Exception {

        String result = null;

        result = baiduTranslateDemo.translate(q, "zh", "en");

        return result;
    }

    public static String translateToChinese(String q) throws Exception {


        String result = null;

        result = baiduTranslateDemo.translate(q, "en", "zh");

        return result;
    }

}
