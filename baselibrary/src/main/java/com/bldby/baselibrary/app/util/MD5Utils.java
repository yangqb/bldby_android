package com.bldby.baselibrary.app.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

/**
 * package name: com.bldby.baselibrary.app.util
 * user: yangqinbo
 * date: 2020/6/15
 * time: 17:57
 * email: 694125155@qq.com
 */
public class MD5Utils {

    public static TreeMap<String, Object> keySort(Map<String, Object> params) {
        params.put("appKey", "BLDBYWOMENYIDINGXING888888");
        params.put("timestamp", System.currentTimeMillis());
        TreeMap<String, Object> treeMap = new TreeMap<>(params);
        return treeMap;
    }

    public static String getSign(TreeMap<String, Object> treeMap) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : treeMap.entrySet()) {
            sb = sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        sb.append("appSecret").append("=").append("BLDBYWOMENYIDINGXING666666");

        return encode(sb.toString());
    }

    /*
     * MD5  32位小写
     * */
    public static String encode(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(text.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : result) {
                int number = b & 0xff;
                String hex = Integer.toHexString(number);
                if (hex.length() == 1) {
                    sb.append("0" + hex);
                } else {
                    sb.append(hex);
                }
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }
}
