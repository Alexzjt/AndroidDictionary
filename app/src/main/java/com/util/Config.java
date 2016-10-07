package com.util;

/**
 * Created by zhangjingtao on 2016/9/30.
 */

public class Config {
    public static final String DICTIONARY="dictionary.txt";
    public static final String WORDFREQUENCY="word_frequency.txt";
    public static final String APPID = "20160928000029493";
    public static final String TOKEN = "EimvmT0tBnwcEvVgX08q";
    public static final String BAIDU = "http://api.fanyi.baidu.com/api/trans/vip/translate";
    /**
     * 本来想做成按键盘距离优先匹配的，无奈结果与给出的示例顺序不符，放弃之
     */
    private static final int DISTANCE=KeyboardSingleton.getInstance().maxDistance;
}
