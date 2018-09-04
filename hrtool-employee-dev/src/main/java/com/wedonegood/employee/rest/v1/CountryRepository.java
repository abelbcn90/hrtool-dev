package com.wedonegood.employee.rest.v1;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CountryRepository extends JpaRepository<Country, Long> {
	
	Country findCountryByCode(final String code);
	
	@Query(value = "SELECT c.* " +
    		" FROM country c " +
    		"   WHERE c.name LIKE %?1% " +
    		" ORDER BY c.name ASC ",
			nativeQuery = true)
	List<Country> findCountriesByName(final String name);
}
