package com.wedonegood.hrtool.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.wedonegood.hrtool.test.UrlLocaleInterceptor;
import com.wedonegood.hrtool.test.UrlLocaleResolver;

/**
 * 
 * @author Abel Pulido Ponce
 *
 */
@Configuration
public class LocaleConfig implements WebMvcConfigurer {

	@Bean(name = "messageSource")
    public MessageSource getMessageResource() {
		final ResourceBundleMessageSource messageResource = new ResourceBundleMessageSource();
 
        // Read i18n/messages_xxx.properties file.
        // For example: i18n/messages_en.properties
        messageResource.setBasenames("i18n/messages", "i18n/roles_messages", "i18n/groups_messages", "i18n/employees_messages");
        messageResource.setDefaultEncoding("UTF-8");
        
        return messageResource;
    }
	
    // To solver URL like:
    // /SomeContextPath/en/login2
    // /SomeContextPath/es/login2
    // /SomeContextPath/fr/login2
    @Bean(name = "localeResolver")
    public LocaleResolver getLocaleResolver() {
        return new UrlLocaleResolver();
    }
    
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
 
    	final UrlLocaleInterceptor localeInterceptor = new UrlLocaleInterceptor();
 
        registry.addInterceptor(localeInterceptor).addPathPatterns("/en/*", "/fr/*", "/es/*");
        registry.addInterceptor(localeInterceptor).excludePathPatterns("/static/*", "/scripts/*");
    }
}
