package com.unifs.behavioranalysis.utils;

import java.util.Random;

/**
 * @创建人 张恭雨
 * @创建时间 2018/11/2
 * @描述：
 */
public class RandomCodeUtil {
    private static final String str="123456789";

    public static String getCode(){
        StringBuilder sb=new StringBuilder(4);
        for(int i=0;i<4;i++)
        {
            char ch=str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }
        return sb.toString();
    }

}
