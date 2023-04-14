package com.yonder.calculate.common.utils;

/**
 * 通用/基础响应码
 *
 * @author mzj
 */
public interface BaseCode {

    /**
     * 成功
     */
    int SUCCESS = 1000;

    /**
     * 参数异常
     */
    int PARAMETER_INVALID = 1001;

    /**
     * 未授权
     */
    int FORBIDDEN = 1003;
    /**
     * 未认证
     */
    int UNAUTHORIZED = 1004;
    /**
     * 服务未知异常
     */
    int SERVER_ERROR = 1005;

}
