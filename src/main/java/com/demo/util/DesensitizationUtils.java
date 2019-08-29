package com.demo.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 银行卡号、身份证号、手机号码、护照号码等敏感信息脱敏工具类
 *
 * @author Shanks
 * @date 2019-04-16
 */
public class DesensitizationUtils {

    /**
     * 阈值
     */
    private static final int THRESHOLD = 8;

    /**
     * 通用阈值
     */
    private static final int THRESHOLD_COMMON = 9;

    /**
     * 手机号码长度
     */
    private static final int PHONE_NO_LENGTH = 11;

    /**
     * 身份证号脱敏正则，可以根据不同场景自定义，脱敏前m位、后n位置，即：?<=\w{m})\w(?=\w{n})
     */
    private static final String REGEX_ID_CARD = "(?<=\\w{6})\\w(?=\\w{4})";

    /**
     * 手机号码脱敏正则
     */
    private static final String REGEX_PHONE = "(\\d{3})\\d{4}(\\d{4})";

    /**
     * 手机号码脱敏
     *
     * @param phoneNo 手机号码
     * @return str
     */
    public static String phoneNo(String phoneNo) {
        if (StringUtils.isEmpty(phoneNo) || (phoneNo.length() != PHONE_NO_LENGTH)) {
            return phoneNo;
        }
        return phoneNo.replaceAll(REGEX_PHONE, "$1****$2");
    }

    /**
     * 身份证号脱敏
     *
     * @param idCardNo 身份证号
     * @return str
     */
    public static String idCardNo(String idCardNo) {
        if (StringUtils.isEmpty(idCardNo) || (idCardNo.length() < THRESHOLD)) {
            return idCardNo;
        }
        return idCardNo.replaceAll(REGEX_ID_CARD, "*");
    }

    /**
     * 护照前2后3位脱敏，护照一般为8或9位
     *
     * @param passportNo 护照
     * @return str
     */
    public static String passportNo(String passportNo) {
        if (StringUtils.isEmpty(passportNo) || (passportNo.length() < THRESHOLD)) {
            return passportNo;
        }
        return passportNo.substring(0, 2) + new String(new char[passportNo.length() - 5]).replace("\0", "*") + passportNo.substring(passportNo.length() - 3);
    }

    /**
     * 敏感信息通用替换方法
     *
     * @param source 源字符串
     * @return str
     */
    public static String common(String source) {
        int len = source.length();
        if (len < THRESHOLD_COMMON) {
            return source;
        }
        return source.replaceAll("(.{" + (len < 12 ? 3 : 6) + "})(.*)(.{4})", "$1****$3");
    }
}

