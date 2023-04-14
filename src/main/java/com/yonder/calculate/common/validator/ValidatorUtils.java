package com.yonder.calculate.common.validator;


import com.yonder.calculate.common.exception.CommonException;
import com.yonder.calculate.common.utils.BaseCode;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.spi.resourceloading.ResourceBundleLocator;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.MessageSourceResourceBundleLocator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * hibernate-validator校验工具类
 * 参考文档：http://docs.jboss.org/hibernate/validator/6.0/reference/en-US/html_single/
 *
 * @author mzj
 */
public class ValidatorUtils {

    private static Validator validator;

    private static ResourceBundleMessageSource messageSource = getMessageSource();

    static {
        validator = Validation.byDefaultProvider().configure().messageInterpolator(
                new LocaleResourceBundleMessageInterpolator(new MessageSourceResourceBundleLocator(messageSource), true))
                .buildValidatorFactory().getValidator();
    }

    private static ResourceBundleMessageSource getMessageSource() {
        ResourceBundleMessageSource bundleMessageSource = new ResourceBundleMessageSource();
        bundleMessageSource.setDefaultEncoding("UTF-8");
        bundleMessageSource.setUseCodeAsDefaultMessage(true);
        bundleMessageSource.setCacheSeconds(5);
        bundleMessageSource.setBasenames("i18n/validation");
        return bundleMessageSource;
    }

    /**
     * 校验对象
     *
     * @param object 待校验对象
     * @param groups 待校验的组
     */
    public static void validateEntity(Object object, Class<?>... groups) throws CommonException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            ConstraintViolation<Object> constraint = constraintViolations.iterator().next();
            throw new CommonException(constraint.getMessage(), BaseCode.PARAMETER_INVALID);
        }
    }

    /**
     * 校验拉列表对象
     *
     * @param list   待校验列表
     * @param groups 待校验的组
     */
    public static void validateEntities(List<?> list, Class<?>... groups)
            throws CommonException {
        for (Object obj : list) {
            validateEntity(obj);
        }
    }

    public static class LocaleResourceBundleMessageInterpolator extends ResourceBundleMessageInterpolator {
        public LocaleResourceBundleMessageInterpolator() {
            super();
        }

        public LocaleResourceBundleMessageInterpolator(ResourceBundleLocator userResourceBundleLocator) {
            super(userResourceBundleLocator);
        }

        public LocaleResourceBundleMessageInterpolator(ResourceBundleLocator userResourceBundleLocator, ResourceBundleLocator contributorResourceBundleLocator) {
            super(userResourceBundleLocator, contributorResourceBundleLocator);
        }

        public LocaleResourceBundleMessageInterpolator(ResourceBundleLocator userResourceBundleLocator, ResourceBundleLocator contributorResourceBundleLocator, boolean cachingEnabled) {
            super(userResourceBundleLocator, contributorResourceBundleLocator, cachingEnabled);
        }

        public LocaleResourceBundleMessageInterpolator(ResourceBundleLocator userResourceBundleLocator, boolean cachingEnabled) {
            super(userResourceBundleLocator, cachingEnabled);
        }

        @Override
        public String interpolate(String message, Context context) {
            return interpolate(message, context, LocaleContextHolder.getLocale());
        }

        @Override
        public String interpolate(String message, Context context, Locale locale) {
            return super.interpolate(message, context, LocaleContextHolder.getLocale());
        }
    }
}