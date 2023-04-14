package com.yonder.calculate.common.exception;

import com.yonder.calculate.common.utils.BaseCode;
import com.yonder.calculate.common.utils.MessageUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 通用异常
 *
 * @author mzj
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CommonException extends RuntimeException {

    private int code;
    private String message;

    public CommonException() {
        this.code = BaseCode.SERVER_ERROR;
        this.message = MessageUtils.getMessage(this.code);
    }

    public CommonException(int code) {
        this.code = code;
        this.message = MessageUtils.getMessage(this.code);
    }

    public CommonException(int code, String... params) {
        this.code = code;
        this.message = MessageUtils.getMessage(this.code, params);
    }

    public CommonException(String message, int code) {
        this.code = code;
        this.message = message;
    }

    public CommonException(String message) {
        this.code = BaseCode.SERVER_ERROR;
        this.message = message;
    }
}
