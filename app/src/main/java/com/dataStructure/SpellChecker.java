package com.dataStructure;

/**
 * Created by zhangjingtao on 2016/9/30.
 */

import android.app.Activity;
import android.content.res.AssetManager;

import com.util.Config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * 拼写纠错
 */
public class SpellChecker {
    private static MetricSpace ms = new LevensteinDistance();
    private static BKTree bk = new BKTree(ms);


    /**
     * 一个阅读文本文件的函数，把所有单词都放入bk树中
     * @param bk
     * @param inputStream
     */
    private static void getWordsFromTxt(BKTree bk, InputStream inputStream){


        BufferedReader reader=null;
        try {
            reader=new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line=reader.readLine())!=null){
                    bk.put(line);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        finally{
            try {
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static BKTree getBKTree(Activity activity) throws Exception{
        bk.clear();
        AssetManager assetManager=activity.getAssets();
        getWordsFromTxt(bk, assetManager.open(Config.DICTIONARY));
        return bk;
    }

    public static boolean checkWord(String string){
        char[] array=string.toCharArray();
        for(char ch : array){
            if(!((ch>='a'&&ch<='z')||(ch>='A'&&ch<='Z'))) {
                return false;
            }
        }
        return true;
    }

    public static List<String> getList(String string, Set<String> set){
        List<String> list = new ArrayList<String>(set);
        final String str=string;
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                if(lhs.length()==rhs.length()){
                    return lhs.compareTo(rhs);
                }
                else{
                    if(lhs.length()==str.length())
                        return -1;
                    else
                        return 1;
                }
            }
        });
        return list;
    }
}
