package com.wedonegood.common.language;

import java.util.List;

public interface LanguageService {
	List<Language> getLanguages();
	Language findLanguageByCode(final String code);
}
