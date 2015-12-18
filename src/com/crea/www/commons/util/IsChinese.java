package com.crea.www.commons.util;

/**
 * @author hp
 * @TODO 判断一个字符串是否包含汉字
 */
public class IsChinese {

    public static boolean isChinese(char a) {
        int v = (int)a;
        return (v >=19968 && v <= 171941);
    }

    public static boolean containsChinese(String s){
        if (null == s || "".equals(s.trim())) return false;
        for (int i = 0; i < s.length(); i++) {
            if (isChinese(s.charAt(i))) return true;
        }
        return false;
    }

}
