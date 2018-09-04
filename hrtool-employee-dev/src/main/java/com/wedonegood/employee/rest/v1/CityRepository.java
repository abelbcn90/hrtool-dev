package com.wedonegood.employee.rest.v1;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CityRepository extends JpaRepository<City, Long> {
	
	City findCityById(final Long cityId);
	List<City> getCitiesByCountryCode(final String countryCode);
	
	@Query(value = "SELECT c.* " +
    		" FROM city c " +
    		"   WHERE c.country_code = ?1 " +
    		"     AND c.name LIKE %?2% " +
    		" ORDER BY c.name ASC ",
			nativeQuery = true)
	List<City> getCitiesByCountryCodeAndName(final String countryCode, final String name);
}
