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
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * 拼写纠错
 */
public class SpellChecker {
    private static MetricSpace ms = new LevensteinDistance();
    private static BKTree bk = new BKTree(ms);
    private static HashMap<String,Integer> map=new HashMap<String, Integer>();


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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 一个阅读文本文件的函数，读词频表放入HashMap
     * @param map1
     * @param inputStream
     */
    private static void getWordFrequencyFromTxt(HashMap<String,Integer> map1, InputStream inputStream){
        BufferedReader reader=null;
        int rank=0;
        try {
            reader=new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line=reader.readLine())!=null){
                if(!map1.containsKey(line))
                    map1.put(line,rank++);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        finally{
            try {
                reader.close();
            } catch (IOException e) {
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

    public static HashMap<String,Integer> getWordFrequency(Activity activity) throws Exception{
        map.clear();
        AssetManager assetManager=activity.getAssets();
        getWordFrequencyFromTxt(map, assetManager.open(Config.WORDFREQUENCY));
        return map;
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

    public static void sortList(List<String> list, final HashMap<String,Integer> hashMap, final String term){
        final HashMap<String,Integer> hashMap1=hashMap;
        final String term1 = term;
//        ArrayList<Integer> arrayList=new ArrayList<Integer>();
//        HashMap<Integer,ArrayList<Integer>> length_list_map = new HashMap<Integer, ArrayList<Integer>>();
//        for(String string : list){
//            if(!arrayList.contains(string.length())){
//                arrayList.add(string.length());
//            }
//            if(length_list_map.containsKey(string.length())){
//
//            }
//            else{
//
//            }
//        }

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                if(lhs.equals(rhs))
                    return 0;
                if(lhs.length()!=rhs.length()){
                    if(term1.length()==lhs.length())
                        return -1;
                    else if(term1.length()==rhs.length())
                        return 1;
                }
                int num1 = hashMap1.containsKey(lhs)?hashMap1.get(lhs):Integer.MAX_VALUE;
                int num2 = hashMap1.containsKey(rhs)?hashMap1.get(rhs):Integer.MAX_VALUE;
                if(num1<num2)
                    return -1;
                else if(num1>num2)
                    return 1;
                else
                    return lhs.compareTo(rhs);
            }
        });
    }

    public static void addAllWithoutRepeat(ArrayList<String> list1,ArrayList<String> list2,String term){
        list1.ensureCapacity(list1.size()+list2.size());
        for(String string : list2){
            if(!list1.contains(string)){
                list1.add(string);
            }
        }
        list1.remove(term);
    }
}
