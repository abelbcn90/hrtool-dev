package com.wedonegood.employee.rest.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {
	
	@Autowired
    private CountryRepository countryRepository;
	
	@Override
	public List<Country> getCountries() {
		return this.countryRepository.findAll();
	}
	
	@Override
	public Country findCountryByCode(final String code) {
		return this.countryRepository.findCountryByCode(code);
	}
	
	@Override
	public List<Country> findCountriesByName(final String name) {
		return this.countryRepository.findCountriesByName(name);
	}
	
	@Override
	public List<Country> saveAll(final List<Country> countryList) {
		return this.countryRepository.saveAll(countryList);
	}
}
