package com.yonder.calculate.common.utils;


import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * 获取i18n资源文件
 *
 */
public class MessageUtils {



    private static MessageSource messageSource;

    static {
        messageSource = SpringUtils.getBean(MessageSource.class);
    }

    /**
     * 根据消息键和参数 获取消息 委托给spring messageSource
     * @param code 消息键
     * @return 获取国际化翻译值
     */
    public static String getMessage(int code) {
        return getMessage(code, new String[0]);
    }

    public static String getMessage(int code, String... params) {
        return messageSource.getMessage(code + "", params, LocaleContextHolder.getLocale());
    }

    public static String getMessage(String key) {
        return messageSource.getMessage(key, new String[0], LocaleContextHolder.getLocale());
    }

}

