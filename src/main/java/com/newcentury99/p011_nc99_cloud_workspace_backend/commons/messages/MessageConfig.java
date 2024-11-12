package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.messages;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class MessageConfig implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localResolver=new SessionLocaleResolver();
        localResolver.setDefaultLocale(Locale.US);
        return localResolver;
    }

    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(getValidationMsgSrc());
        return bean;
    }

    @Bean
    public static MessageSource getValidationMsgSrc() {
        ReloadableResourceBundleMessageSource msgSrc = new ReloadableResourceBundleMessageSource();
        msgSrc.setUseCodeAsDefaultMessage(true);
        msgSrc.setBasenames(
                "messages/global/validations",
                "messages/users/validations",
                "messages/storages/validations",
                "messages/schedules/validations",
                "messages/shares/validations",
                "messages/academics/validations"
        );
        return msgSrc;
    }

    @Bean
    public static MessageSource getErrorMsgSrc() {
        ReloadableResourceBundleMessageSource msgSrc = new ReloadableResourceBundleMessageSource();
        msgSrc.setUseCodeAsDefaultMessage(true);
        msgSrc.setBasenames(
                "messages/global/errors",
                "messages/users/errors",
                "messages/storages/errors",
                "messages/schedules/errors",
                "messages/shares/errors",
                "messages/academics/errors"
        );
        return msgSrc;
    }

    @Bean
    public static MessageSource getResponseMsgSrc() {
        ReloadableResourceBundleMessageSource msgSrc = new ReloadableResourceBundleMessageSource();
        msgSrc.setUseCodeAsDefaultMessage(true);
        msgSrc.setBasenames(
                "messages/global/responses",
                "messages/users/responses",
                "messages/storages/responses",
                "messages/schedules/responses",
                "messages/shares/responses",
                "messages/academics/responses"
        );
        return msgSrc;
    }

    @Bean
    public static MessageSource getStorageMsgSrc() {
        ReloadableResourceBundleMessageSource msgSrc = new ReloadableResourceBundleMessageSource();
        msgSrc.setUseCodeAsDefaultMessage(true);
        msgSrc.setBasenames(
                "messages/global/validations",
                "messages/storages/validations",
                "messages/storages/responses",
                "messages/storages/errors"
        );
        return msgSrc;
    }

    @Bean
    public static MessageSource getUserMsgSrc() {
        ReloadableResourceBundleMessageSource msgSrc = new ReloadableResourceBundleMessageSource();
        msgSrc.setUseCodeAsDefaultMessage(true);
        msgSrc.setBasenames(
                "messages/global/validations",
                "messages/users/validations",
                "messages/users/responses",
                "messages/users/errors"
        );
        return msgSrc;
    }

    @Bean
    public static MessageSource getSchedulesMsgSrc() {
        ReloadableResourceBundleMessageSource msgSrc = new ReloadableResourceBundleMessageSource();
        msgSrc.setUseCodeAsDefaultMessage(true);
        msgSrc.setBasenames(
                "messages/global/validations",
                "messages/schedules/validations",
                "messages/schedules/responses",
                "messages/schedules/errors"
        );
        return msgSrc;
    }

    @Bean
    public static MessageSource getAcademicsMsgSrc() {
        ReloadableResourceBundleMessageSource msgSrc = new ReloadableResourceBundleMessageSource();
        msgSrc.setUseCodeAsDefaultMessage(true);
        msgSrc.setBasenames(
                "messages/global/validations",
                "messages/academics/validations",
                "messages/academics/responses",
                "messages/academics/errors"
        );
        return msgSrc;
    }
}
