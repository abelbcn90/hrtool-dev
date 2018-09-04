package com.wedonegood.employee.rest.v1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.minidev.json.parser.JSONParser;

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
	
	private final static String FOLDER_HOME = "folder.home";
	private final static String FILE_NAME_CITIES = "file.name.cities";
	
	@Autowired
	private Environment env;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private CityService cityService;
	
//	@GetMapping({"/list/", "/list/{countryCode}"})
//    @ApiOperation(value = "Filter cities", nickname = "filterCities")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "List of cities filtered by country code", response = CityDto[].class, responseContainer = "List")
//    })
//    public ResponseEntity<List<CityDto>> filterCities(@PathVariable(value="countryCode", required = false) final Optional<String> countryCode) throws JsonParseException, JsonMappingException, IOException {
//		List<City> cityList = this.cityService.getCities();
//		
//		if (null == cityList) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        
//		if (countryCode.isPresent()) {
//			cityList = this.cityService.getCitiesByCountryCode(countryCode.get());
//		}
//		
//		final List<CityDto> cityDtoList = new ArrayList<CityDto>();
//        for (final City c : cityList) {
//        	cityDtoList.add(new CityDto(c));
//        }
//		
//        return ResponseEntity.ok(cityDtoList);
//    }
	
	@GetMapping({"/list/", "/list/{countryCode}"})
    @ApiOperation(value = "Filter cities", nickname = "filterCities")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of cities filtered by country code", response = CityJson[].class, responseContainer = "List")
    })
    public ResponseEntity<List<CityJson>> filterCities(@PathVariable(value="countryCode", required = false) final Optional<String> countryCode) throws Exception {
		String csvFile = "subdivision-codes_csv.csv";
        String line = "";
        String cvsSplitBy = ",";
        
        List<City> cityList = new ArrayList<City>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(new ClassPathResource(csvFile).getFile()))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);

                System.out.println("Country [code= " + country[0] + " , name=" + country[2] + "]");
                cityList.add(new City(new Country(country[0]), country[2]));
            }
        }
        
        this.cityService.saveAll(cityList);
        
        return ResponseEntity.ok(null);
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
