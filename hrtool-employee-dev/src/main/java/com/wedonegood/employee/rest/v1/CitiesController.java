package com.wedonegood.employee.rest.v1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author Abel Pulido Ponce
 *
 */
@Transactional
@RestController
@RequestMapping(value = "/api/v1/cities")
@Api(value="City", description="Operations pertaining to Cities", position = 3)
public class CitiesController {
	
	@Autowired
	private CityService cityService;
	
	@GetMapping({"/list/", "/list/{countryCode}"})
    @ApiOperation(value = "Filter cities", nickname = "filterCities")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of cities filtered by country code", response = CityDto[].class, responseContainer = "List")
    })
    public ResponseEntity<List<CityDto>> filterCities(@PathVariable(value="countryCode", required = false) final Optional<String> countryCode) throws JsonParseException, JsonMappingException, IOException {
		List<City> cityList = this.cityService.getCities();
		
		if (null == cityList) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
		if (countryCode.isPresent()) {
			cityList = this.cityService.getCitiesByCountryCode(countryCode.get());
		}
		
		final List<CityDto> cityDtoList = new ArrayList<CityDto>();
        for (final City c : cityList) {
        	cityDtoList.add(new CityDto(c));
        }
		
        return ResponseEntity.ok(cityDtoList);
    }
	
	@GetMapping("/list/{countryCode}/{name}")
	@ApiOperation(value = "Filter cities", nickname = "filterCitiesByName")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "List of cities filtered by country code", response = CityDto[].class, responseContainer = "List")
	})
	public ResponseEntity<List<CityDto>> filterCitiesByName(@PathVariable("countryCode") final Optional<String> countryCode, @PathVariable("name") final Optional<String> name) throws JsonParseException, JsonMappingException, IOException {
		List<City> cityList = this.cityService.getCities();
		
		if (null == cityList) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
		if (countryCode.isPresent() && name.isPresent()) {
			cityList = this.cityService.getCitiesByCountryCodeAndName(countryCode.get(), name.get());
		}
		
		final List<CityDto> cityDtoList = new ArrayList<CityDto>();
        for (final City c : cityList) {
        	cityDtoList.add(new CityDto(c));
        }
		
        return ResponseEntity.ok(cityDtoList);
	}
}
