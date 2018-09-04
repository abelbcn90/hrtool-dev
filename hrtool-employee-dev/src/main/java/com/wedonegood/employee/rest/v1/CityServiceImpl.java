package com.wedonegood.employee.rest.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {
	
	@Autowired
    private CityRepository cityRepository;
	
	@Override
	public List<City> getCities() {
		return this.cityRepository.findAll();
	}
	
	@Override
	public City findCityById(final Long cityId) {
		return this.cityRepository.findCityById(cityId);
	}
	
	@Override
	public List<City> getCitiesByCountryCode(final String countryCode) {
		return this.cityRepository.getCitiesByCountryCode(countryCode);
	}
	
	@Override
	public List<City> getCitiesByCountryCodeAndName(final String countryCode, final String name) {
		return this.cityRepository.getCitiesByCountryCodeAndName(countryCode, name);
	}
	
	@Override
	public List<City> saveAll(final List<City> cityList) {
		return this.cityRepository.saveAll(cityList);
	}
}
