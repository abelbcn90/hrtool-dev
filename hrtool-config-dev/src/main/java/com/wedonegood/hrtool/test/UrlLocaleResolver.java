package com.wedonegood.hrtool.test;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.LocaleResolver;

/**
 * 
 * @author Abel Pulido Ponce
 *
 */
public class UrlLocaleResolver implements LocaleResolver {

	private static final String URL_LOCALE_ATTRIBUTE_NAME = "URL_LOCALE_ATTRIBUTE_NAME";

	@Override
	public Locale resolveLocale(final HttpServletRequest request) {
		// ==> /SomeContextPath/en/...
		// ==> /SomeContextPath/fr/...
		// ==> /SomeContextPath/WEB-INF/pages/...
		final String uri = request.getRequestURI();
 
		System.out.println("URI=" + uri);

		final String prefixEn = request.getServletContext().getContextPath() + "/en/";
		final String prefixFr = request.getServletContext().getContextPath() + "/fr/";
		final String prefixEs = request.getServletContext().getContextPath() + "/es/";

		Locale locale = null;

		// English
		if (uri.startsWith(prefixEn)) {
			locale = Locale.ENGLISH;
		}
		// French
		else if (uri.startsWith(prefixFr)) {
			locale = Locale.FRANCE;
		}
		// Spanish
		else if (uri.startsWith(prefixEs)) {
			locale = new Locale("es", "ES");
		}
		
		if (null != locale) {
			request.getSession().setAttribute(URL_LOCALE_ATTRIBUTE_NAME, locale);
		}
		
		if (null == locale) {
			locale = (Locale) request.getSession().getAttribute(URL_LOCALE_ATTRIBUTE_NAME);
			if (null == locale) {
				locale = Locale.ENGLISH;
			}
		}
		
		return locale;
	}

	@Override
	public void setLocale(final HttpServletRequest request, final HttpServletResponse response, final Locale locale) {
		// Nothing
	}
}