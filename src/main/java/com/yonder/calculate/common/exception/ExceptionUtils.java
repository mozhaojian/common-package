/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.yonder.calculate.common.exception;

import org.apache.commons.io.IOUtils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Exception工具类
 *
 * @author mzj
 */
public class ExceptionUtils {

    /**
     * 获取异常信息
     *
     * @param ex 异常
     * @return 返回异常信息
     */
    public static String getErrorStackTrace(Exception ex) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw, true);
            ex.printStackTrace(pw);
        } finally {
            IOUtils.closeQuietly(pw);
            IOUtils.closeQuietly(sw);
        }
        return sw.toString();
    }
}