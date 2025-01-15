package com.linjiasong.user.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author linjiasong
 * @date 2025/1/15 下午2:37
 */
public class MD5Util {

    private static final MessageDigest MD5;

    static {
        try {
            MD5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String md5Digest(String input) {
        byte[] digest = MD5.digest(input.getBytes());

        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

}
