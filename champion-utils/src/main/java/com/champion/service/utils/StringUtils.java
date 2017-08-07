package com.champion.service.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yongjie.pei on 2017/8/7.
 */
public class StringUtils {
    public static boolean chackStringNull(String checkString){
        if(checkString!=null && !"".equals(checkString.trim()) && !"null".equals(checkString.trim())){
            return true;
        }
        return false;
    }

    public static String md5Encoding(String encodString) throws NoSuchAlgorithmException {
        String md5Index = "";
        if(encodString!=null && !"".equals(encodString.trim()) && !"null".equals(encodString.trim())){
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(encodString.getBytes());
            BigInteger md5Int = new BigInteger(1, md5.digest());
            md5Index = String.format("%1$032X", md5Int);
        }
        return md5Index;
    }
}
