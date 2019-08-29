package com.demo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

/**
 * @author zhoukai
 * @date 2018-09-20
 */
public class Md5Utils {

    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final char[] UPPER_CASE_HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String md5(String s) {
        return md5(s, HEX_DIGITS);
    }

    public static String md5UpperCase(String s) {
        return md5(s, UPPER_CASE_HEX_DIGITS);
    }

    private static String md5(String s, char[] hexDigits) {
        try {
            byte[] bytes = s.getBytes();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(bytes);
            byte[] md5Bytes = md5.digest();
            char[] chars = new char[md5Bytes.length * 2];
            int k = 0;
            for (byte byte0 : md5Bytes) {
                chars[k++] = hexDigits[byte0 >>> 4 & 0xf];
                chars[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(chars);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String md5(byte[] bytes) {
        String value = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(bytes);
            BigInteger bigInteger = new BigInteger(1, md5.digest());
            value = bigInteger.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String md5(File file) throws FileNotFoundException {
        String value = null;
        FileInputStream in = new FileInputStream(file);
        try {
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bigInteger = new BigInteger(1, md5.digest());
            value = bigInteger.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    public static String nanoTimeMd5() {
        return md5(String.valueOf(System.nanoTime()));
    }

    public static String nanoTimeMd5UpperCase() {
        return md5UpperCase(String.valueOf(System.nanoTime()));
    }
}
