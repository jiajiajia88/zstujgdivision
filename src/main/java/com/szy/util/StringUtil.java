package com.szy.util;

import org.springframework.util.StringUtils;

/**
 * 集合工具类
 * Created by lll on 2016/10/16.
 */
public final class StringUtil {

    /**
     * 判断字符串是否为空
     */
    public static boolean isEmpty(String str){
        if(str!=null){
            str=str.trim();
        }
        return StringUtils.isEmpty(str);
    }

    /**
     *判断字符串是否非空
     */
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}
