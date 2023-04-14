package com.yonder.calculate.common.utils;

import com.yonder.calculate.common.exception.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Bean属性浅拷贝.深拷贝,考虑后续支持
 *
 * @author mzj
 */
@Slf4j
public class BeanCopyUtils {

    public static <T> T copy(Object source, Class<T> target) {
        if (source == null) {
            return null;
        }
        T obj = null;
        try {
            obj = target.newInstance();
            BeanUtils.copyProperties(source, obj);
        } catch (Exception e) {
            log.error(ExceptionUtils.getErrorStackTrace(e));
        }
        return obj;
    }

    public static <T> List<T> copyList(List<? extends Object> sources, Class<T> target) {
        if (sources == null || CollectionUtils.isEmpty(sources)) {
            return Collections.EMPTY_LIST;
        }
        return sources.stream().map(obj -> copy(obj, target)).collect(Collectors.toList());
    }
}
