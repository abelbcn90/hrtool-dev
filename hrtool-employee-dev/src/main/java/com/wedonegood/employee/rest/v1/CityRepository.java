package com.wedonegood.employee.rest.v1;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
	
	City findCityById(final Long cityId);
	List<City> getCitiesByCountryCode(final String countryCode);
	List<City> getCitiesByCountryCodeAndName(final String countryCode, final String name);
}
