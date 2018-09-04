package com.wedonegood.employee.rest.v1;

import java.util.List;

public interface CityService {
	List<City> getCities();
	City findCityById(final Long cityId);
	List<City> getCitiesByCountryCode(final String countryCode);
	List<City> getCitiesByCountryCodeAndName(final String countryCode, final String name);
	List<City> saveAll(final List<City> cityList);
}
