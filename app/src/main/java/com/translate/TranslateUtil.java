package com.translate;

/**
 * Created by zhangjingtao on 2016/9/30.
 */

public class TranslateUtil {
    public static String tarnslate(String string){
        try {
            String result = BaiduTranslateDemo.translateToChinese(string);
            if(result==null)
                throw new Exception();
            return result;
        }
        catch(Exception ex){
            return "啊哦！翻译过程出错了";
        }
    }
}
