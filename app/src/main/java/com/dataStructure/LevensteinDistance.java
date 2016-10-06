package com.dataStructure;
import com.util.Config;

import java.util.HashMap;
import java.util.Map;
/**
 * Created by zhangjingtao on 2016/9/30.
 */

/**
 * 编辑距离， 又称Levenshtein距离，是指两个字串之间，由一个转成另一个所需的最少编辑操作次数。
 * 该类中许可的编辑操作包括将一个字符替换成另一个字符，插入一个字符，删除一个字符。
 *
 * 使用动态规划算法。算法复杂度：m*n。
 *
 */
public class LevensteinDistance implements MetricSpace{
    private double insertCost = 1;       // 插入的距离
    private double deleteCost = 1;       // 删除的距离
    private static final Map<Character, String> charSiblings;
    static {
        charSiblings = new HashMap<Character, String>();
        charSiblings.put('q', "was");
        charSiblings.put('w', "qsead");
        charSiblings.put('e', "wsdfr");
        charSiblings.put('r', "edfgt");
        charSiblings.put('t', "rfghy");
        charSiblings.put('y', "tghju");
        charSiblings.put('u', "yhjki");
        charSiblings.put('i', "ujklo");
        charSiblings.put('o', "ikl;p");
        charSiblings.put('p', "ol;'[");
        charSiblings.put('a', "qwsxz");
        charSiblings.put('s', "qazxcdew");
        charSiblings.put('d', "wsxcvfre");
        charSiblings.put('f', "edcvbgtr");
        charSiblings.put('g', "rfvbnhyt");
        charSiblings.put('h', "tgbnmjuy");
        charSiblings.put('j', "yhnm,kiu");
        charSiblings.put('k', "ujm,.loi");
        charSiblings.put('l', "ik,./;po");
        charSiblings.put('z', "asx");
        charSiblings.put('x', "zasdc");
        charSiblings.put('c', "xsdfv");
        charSiblings.put('v', "cdfgb");
        charSiblings.put('b', "vfghn");
        charSiblings.put('n', "bghjm");
        charSiblings.put('m', "nhjk,");
    }
    private double substitudeCost(char char1,char char2){     //替换的距离
        if (char1 == char2) {
            return 0;
        }
        String s = charSiblings.get(char2);
        if (s != null && s.indexOf(char2) > -1) {
            return Config.SCORE_MIS_HIT;
        }
        return 1;
    }

    public double computeDistance(String target,String source){
        int n = target.trim().length();
        int m = source.trim().length();

        double[][] distance = new double[n+1][m+1];

        distance[0][0] = 0;
        for(int i = 1; i <= m; i++){
            distance[0][i] = i;
        }
        for(int j = 1; j <= n; j++){
            distance[j][0] = j;
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <=m; j++){
                double min = distance[i-1][j] + insertCost;

                if(target.charAt(i-1) == source.charAt(j-1)){
                    if(min > distance[i-1][j-1])
                        min = distance[i-1][j-1];
                }else{
                    if(min > distance[i-1][j-1] + substitudeCost(target.charAt(i-1),source.charAt(j-1)))
                        min = distance[i-1][j-1] + substitudeCost(target.charAt(i-1),source.charAt(j-1));
                }

                if(min > distance[i][j-1] + deleteCost){
                    min = distance[i][j-1] + deleteCost;
                }

                distance[i][j] = min;
            }
        }

        return distance[n][m];
    }

    @Override
    public double distance(String a, String b) {
        return computeDistance(a,b);
    }

    public static void main(String[] args) {
        LevensteinDistance distance = new LevensteinDistance();
        System.out.println(distance.computeDistance("你好","好你"));
    }
}
