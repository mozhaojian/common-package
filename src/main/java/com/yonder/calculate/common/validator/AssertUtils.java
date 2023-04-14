package com.yonder.calculate.common.validator;


import com.yonder.calculate.common.exception.CommonException;
import com.yonder.calculate.common.utils.BaseCode;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * 校验工具类
 *
 * @author mzj
 */
public class AssertUtils {

    public static void bool(boolean bool, String... params) {
        if (bool) {
            throw new CommonException(BaseCode.PARAMETER_INVALID, params);
        }
    }

    public static void isBlank(String str, String... params) {
        if (StringUtils.isBlank(str)) {
            throw new CommonException(BaseCode.PARAMETER_INVALID, params);
        }
    }

    public static void isNull(Object object, String... params) {
        if (object == null) {
            throw new CommonException(BaseCode.PARAMETER_INVALID, params);
        }
    }

    public static void isArrayEmpty(Object[] array, String... params) {
        isArrayEmpty(array, BaseCode.PARAMETER_INVALID, params);
    }

    public static void isArrayEmpty(Object[] array, Integer code, String... params) {
        if (ArrayUtils.isEmpty(array)) {
            throw new CommonException(code, params);
        }
    }

    public static void isListEmpty(List<?> list, String... params) {
        isListEmpty(list, BaseCode.PARAMETER_INVALID, params);
    }

    public static void isListEmpty(List<?> list, int code, String... params) {
        if (CollectionUtils.isEmpty(list)) {
            throw new CommonException(code, params);
        }
    }

    public static void isMapEmpty(Map map, String... params) {
        isMapEmpty(map, BaseCode.PARAMETER_INVALID, params);
    }

    public static void isMapEmpty(Map map, Integer code, String... params) {
        if (CollectionUtils.isEmpty(map)) {
            throw new CommonException(code, params);
        }
    }

    public static void validateList(List<?> list, Class<?>... groups) {
        isListEmpty(list);
        for (Object obj : list) {
            ValidatorUtils.validateEntity(obj, groups);
        }
    }
}