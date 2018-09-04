package com.wedonegood.employee.rest.v1;

import java.util.List;

public interface CountryService {
	List<Country> getCountries();
	Country findCountryByCode(final String code);
	List<Country> findCountriesByName(final String name);
	List<Country> saveAll(final List<Country> countryList);
}
