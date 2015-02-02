package com.nuoyu.utopia.utopiasso.common.utils;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by liuxin3 on 2015/1/28.
 * 一些字符串处理工具类  字符截取。。。防止xss输入
 */
public class StringUtil {

    private static final Logger log = LoggerFactory.getLogger(StringUtil.class);

    // 输入黑名单，将这些符号转成ASCII码
    private static String[] intruders = {"&", "<", ">", "\""};

    /**
     * 过滤intruder中的输入，一定意义上防xss,富文本慎用此方法
     *
     * @param input
     * @return
     */
    public static String xssEncode(String input) {
        String output = input;
        for (String intruder : intruders) {
            // 转成ASCII码
            output = output.replaceAll(intruder, StringEscapeUtils.escapeHtml(intruder));
        }
        return output;
    }

    /**
     * 字符截取 一个不区分汉字和英文
     *
     * @param input
     * @param number
     * @param end    结尾追加的符号
     * @return
     */
    public static String subString(String input, int number, String end) {
        String output = "";
        if (StringUtils.isNotBlank(input)) {
            if (number < input.length()) {
                output = input.substring(0, number);
                // 追加结尾符号
                if (StringUtils.isNotBlank(end)) {
                    output += end;
                }
            } else {
                // 如果number>=input长度 不截取  不追加
                output = input;
            }
        }
        return output;
    }

    /**
     * 字符截取 不区分汉字和英文 结尾不追加
     *
     * @param input
     * @param number
     * @return
     */
    public static String subString(String input, int number) {
        return subString(input, number, "");
    }

    /**
     * 截取字符串 按字符长度截取  如果当前长度截取会导致半个汉字 则截取number-1
     *
     * @param input
     * @param number
     * @param end
     * @return
     */
    public static String subStringByLength(String input, int number, String end) {
        String output = "";
        int count = 0;
        if (StringUtils.isNotBlank(input)) {
            char[] tempArray = new char[input.length()];
            char[] charArray = input.toCharArray();
            try {
                for (int i = 0; i < charArray.length; i++) {
                    byte[] bt = String.valueOf(charArray[i]).getBytes("GBK");
                    if (count + bt.length <= number) {
                        tempArray[i] = charArray[i];
                        count += bt.length;
                    } else {
                        break;
                    }
                }
                output = new String(tempArray).trim();
                // 如果未截取则不追加end
                if (!input.equals(output)) {
                    output += end;
                }
            } catch (UnsupportedEncodingException e) {
                // 异常返回原字符串 不截取
                output = input;
                log.error("StringUtil.subStringByLength 截取字符串失败===>input:" + input + "===>number:" + number + "===>end:" + end);
            }
        }
        return output;
    }

    /**
     * 截取字符串 按字符长度截取  如果当前长度截取会导致半个汉字 则截取number-1  不追加任何字符
     *
     * @param input
     * @param number
     * @return
     */
    public static String subStringByLength(String input, int number) {
        return subStringByLength(input, number, "");
    }

}
