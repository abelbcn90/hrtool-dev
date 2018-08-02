package com.wedonegood.hrtool.test;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * 
 * @author Abel Pulido Ponce
 *
 */
public class UrlLocaleInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {

		final LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);

		if (null == localeResolver) {
			throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
		}

		// Get Locale from LocaleResolver
		final Locale locale = localeResolver.resolveLocale(request);

		localeResolver.setLocale(request, response, locale);

		return true;
	}

}