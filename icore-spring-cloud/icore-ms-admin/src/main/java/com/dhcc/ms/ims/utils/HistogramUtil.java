package com.dhcc.ms.ims.utils;

// 直方图工具类
public class HistogramUtil {
    public static long[] add(long[] dest,long[] source){
        if(dest!=null && source!=null){
            for(int i=0;i<source.length;i++){
                dest[i] += source[i];
            }
            return dest;
        }
        return null;
    }
}
