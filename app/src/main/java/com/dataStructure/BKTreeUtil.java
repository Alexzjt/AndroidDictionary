package com.dataStructure;

import java.util.HashMap;

/**
 * Created by zhangjingtao on 2016/10/6.
 */

public class BKTreeUtil {
    char[][] keyboard = {{'q','w','e','r','t','y','u','i','o','p'}
            ,{'a','s','d','f','g','h','j','k','l'},{'z','x','c','v','b','n','m'}};
    HashMap<Character,Integer[]> letter_location = new HashMap<Character, Integer[]>(26);



    private class Location{
        int x,y;
        Location(int num1,int num2){
            x=num1;
            y=num2;
        }
    }
}
