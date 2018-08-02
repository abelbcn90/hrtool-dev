package com.wedonegood.common.language;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
	
	Language findLanguageByCode(final String code);
}
