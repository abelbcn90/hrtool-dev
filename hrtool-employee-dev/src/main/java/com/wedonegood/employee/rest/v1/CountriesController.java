package com.wedonegood.employee.rest.v1;

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
@RequestMapping(value = "/api/v1/countries")
@Api(value="Country", description="Operations pertaining to Countries", position = 3)
public class CountriesController {
	
	@Autowired
	private CountryService countryService;
	
	@GetMapping({"/list/", "/list/{name}"})
    @ApiOperation(value = "Filter countries", nickname = "filterCountries")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of countries filtered by name", response = CountryDto[].class, responseContainer = "List")
    })
	public ResponseEntity<List<CountryDto>> filterCountries(@PathVariable(value="name", required = false) final Optional<String> name) {
		List<Country> countryList = this.countryService.getCountries();
		
		if (null == countryList) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
		if (name.isPresent()) {
			countryList = this.countryService.findCountriesByName(name.get());
		}
		
		final List<CountryDto> countryDtoList = new ArrayList<CountryDto>();
        for (final Country c : countryList) {
        	countryDtoList.add(new CountryDto(c));
        }
		
        return ResponseEntity.ok(countryDtoList);
    }
}
