package com.dataStructure;

/**
 * Created by zhangjingtao on 2016/9/30.
 */

import com.util.Config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

/**
 * 拼写纠错
 */
public class SpellChecker {
    private static MetricSpace<String> ms = new LevensteinDistance();
    private static BKTree<String> bk = new BKTree<String>(ms);


    /**
     * 一个阅读文本文件的函数，把所有单词都放入bk树中
     * @param bk
     * @param path
     */
    private static void getWordsFromTxt(BKTree<String> bk,String path){
        BufferedReader reader=null;
        try {
            reader=new BufferedReader(new FileReader(path));
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

    public static BKTree<String> getBKTree(){
        bk.clear();
        getWordsFromTxt(bk, Config.DICTIONARY);
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

    public static void main(String args[]) {
        double radius = 1.5; // 编辑距离阈值
        String term = "helli"; // 待纠错的词

        // 创建BK树
        MetricSpace<String> ms = new LevensteinDistance();
        BKTree<String> bk = new BKTree<String>(ms);

        getWordsFromTxt(bk, Config.DICTIONARY);

        Scanner cin=new Scanner(System.in);
        while(cin.hasNext()){
            radius=cin.nextDouble();
            term=cin.next();
            Set<String> set = bk.query(term, radius);
            System.out.println(set.toString());
        }
    }
}
