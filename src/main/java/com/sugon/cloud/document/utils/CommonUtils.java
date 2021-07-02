package com.sugon.cloud.document.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author : Daniel Pei / Peixq
 * Email : peixq1222@icloud.com
 * Create : 2018/3/14 上午11:31
 */
public class CommonUtils
{
    private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);

    /**
     * 转换文件大小
     * @param size
     * @return
     */
    public static String FormatFileSize(long size)
    {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize="0B";
        if(size==0){
            return wrongSize;
        }
        if (size < 1024){
            fileSizeString = df.format((double) size) + "B";
        } else if (size < 1048576){
            fileSizeString = df.format((double) size / 1024) + "KB";
        } else if (size < 1073741824){
            fileSizeString = df.format((double) size / 1048576) + "MB";
        } else{
            fileSizeString = df.format((double) size / 1073741824) + "GB";
        }
        return fileSizeString;
    }
    /**
     * 判断字符串中是否包含中文
     * @param str
     * 待校验字符串
     * @return 是否为中文
     * @warn 不能校验是否为中文标点符号
     */
    public static boolean isContainChinese(String str) {
        if(StringUtils.isEmpty(str)) return false;
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

}
