package com.lxkj.wms.utils;

/**
 * Created by kxn on 2020/5/8 0008.
 */
public class PasswordUtil {
    public static final String PW_UppercaseLetters = ".*[A-Z]+.*"; //是否包含大写字母
    public static final String PW_LowercaseLetters = ".*[a-z]+.*"; //是否包含小写字母
    public static final String PW_DigitalNumber = ".*[0-9]+.*"; //是否包含大写字母
    public static final String PW_SpecialCharacters = ".*[~!@#$%^&*()_]+.*"; //是否包含大写字母

    public static boolean isContainUp(String content) {
        return content.matches(PW_UppercaseLetters);
    }

    public static boolean isContainLower(String content) {
        return content.matches(PW_LowercaseLetters);
    }

    public static boolean isContainDigitalNumber(String content) {
        return content.matches(PW_DigitalNumber);
    }

    public static boolean isContainSpecialCharacters(String content) {
        return content.matches(PW_SpecialCharacters);
    }

    public static boolean isLength(String content, int minLength, int maxLength) {
        if (content.length() >= minLength && content.length() <= maxLength)
            return true;
        else
            return false;
    }

}
