package com.wedonegood.common.language;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageServiceImpl implements LanguageService {
	
	@Autowired
    private LanguageRepository languageRepository;
	
	@Override
	public List<Language> getLanguages() {
		return this.languageRepository.findAll();
	}
	
	@Override
	public Language findLanguageByCode(final String code) {
		return this.languageRepository.findLanguageByCode(code);
	}
}
