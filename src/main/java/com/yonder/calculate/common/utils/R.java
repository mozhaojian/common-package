package com.yonder.calculate.common.utils;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 请求响应对象
 *
 * @author mzj
 */
@Data
public class R<T> implements Serializable {
    private int code = BaseCode.SUCCESS;
    private String message = MessageUtils.getMessage(BaseCode.SUCCESS);
    private T data;

    public R() {
    }

    public R(int code) {
        this.code = code;
        this.message = MessageUtils.getMessage(this.code);
    }

    public R(int code, String message) {
        this.code = code;
        this.message = StringUtils.isBlank(message) ? MessageUtils.getMessage(this.code) : message;
    }

    public static <T> R<T> success(T t) {
        R r = new R();
        r.setData(t);
        return r;
    }

    public static <T> R<T> error(int code) {
        R r = new R(code);
        return r;
    }

    public static <T> R<T> error(int code, String message) {
        R r = new R(code, message);
        return r;
    }

    public static <T> R<T> error(String message) {
        R r = new R(BaseCode.SERVER_ERROR, message);
        return r;
    }

    public static <T> R<T> error() {
        R r = new R(BaseCode.SERVER_ERROR);
        return r;
    }

    public R replace(Object... params) {
        setMessage(String.format(getMessage(), params));
        return this;
    }
}
