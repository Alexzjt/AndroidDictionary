package com.util;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by zhangjingtao on 2016/10/6.
 */

public class KeyboardSingleton {
    private static final char[][] keyboard2D = {{'q','w','e','r','t','y','u','i','o','p'}
            ,{'a','s','d','f','g','h','j','k','l'},{'z','x','c','v','b','n','m'}};
    private static final char[] keyboard = {'a','b','c','d','e','f','g','h','i','j','k'
            ,'l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    private HashMap<Character,Location> letter_location;
    public HashMap<String,Integer> distanceMap;
    private static KeyboardSingleton keyboardSingleton = new KeyboardSingleton();
    public int maxDistance;

    public static KeyboardSingleton getInstance(){
        return keyboardSingleton;
    }

    private KeyboardSingleton(){
        letter_location = new HashMap<Character, Location>(26);
        distanceMap = new HashMap<String, Integer>();
        for(int i=0;i<keyboard2D.length;i++){
            for(int j=0;j<keyboard2D[i].length;j++){
                letter_location.put(keyboard2D[i][j],new Location(i,j));
            }
        }
        for(int i=0;i<keyboard.length;i++){
            for(int j=i+1;j<keyboard.length;j++){
                String string=String.valueOf(keyboard[i])+String.valueOf(keyboard[j]);
                int distance = letter_location.get(keyboard[i])
                        .distance(letter_location.get(keyboard[j]));
                distanceMap.put(string,distance);
            }
        }
        for(Iterator<Integer> iterator=distanceMap.values().iterator();iterator.hasNext();){
            int temp=iterator.next();
            if(temp>maxDistance){
                maxDistance=temp;
            }
        }
    }

    public int getDistance(char char1,char char2){
        char1=downCase(char1);
        char2=downCase(char2);
        String string = char1<char2?String.valueOf(char1)+String.valueOf(char2)
                :String.valueOf(char2)+String.valueOf(char1);
        if(distanceMap.containsKey(string))
            return distanceMap.get(string);
        else
            return maxDistance;
    }

    private char downCase(char char1){
        if(char1>='A'&&char1<='Z'){
            return (char)(char1-'A'+'a');
        }
        return char1;
    }

    private class Location{
        private int x,y;

        /**
         * 对象在键盘上的坐标
         * @param num1  x坐标
         * @param num2  y坐标
         */
        Location(int num1,int num2){
            x=num1;
            y=num2;
        }

        /**
         * 计算两个Location对象之间的距离的平方
         * @param location  另一个Location对象
         * @return  两个Location对象之间的距离的平方
         */
        int distance(Location location){
            int num = (x-location.x)*(x-location.x);
            num+=(y-location.y)*(y-location.y);
            return num;
        }
    }
}
