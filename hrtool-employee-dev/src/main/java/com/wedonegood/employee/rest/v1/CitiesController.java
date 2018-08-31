package com.wedonegood.employee.rest.v1;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	private final static String FOLDER_HOME = "folder.home";
	private final static String FILE_NAME_CITIES = "file.name.cities";
	
	@Autowired
	private Environment env;
	
	@GetMapping("/list")
	@ApiOperation(value = "Get cities", nickname = "listCities")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "List of cities", response = String[].class, responseContainer = "List")
	})
	public ResponseEntity<List<City>> listCities() throws JsonParseException, JsonMappingException, IOException {
		final ObjectMapper mapper = new ObjectMapper();
//		final List<City> citiesList = mapper.readValue(new ClassPathResource(this.env.getProperty(FILE_NAME_CITIES)).getFile(), new TypeReference<List<City>>(){});
		final List<City> citiesList = mapper.readValue(new File(this.env.getProperty(FOLDER_HOME) + this.env.getProperty(FILE_NAME_CITIES)), new TypeReference<List<City>>(){});
		
		return ResponseEntity.ok(citiesList);
	}
	
	@GetMapping("/list/{countryCode}")
    @ApiOperation(value = "Filter cities", nickname = "filterCities")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of cities filtered by country code", response = String[].class, responseContainer = "List")
    })
    public ResponseEntity<List<City>> filterCities(@PathVariable("countryCode") final String countryCode) throws JsonParseException, JsonMappingException, IOException {
		final ObjectMapper mapper = new ObjectMapper();
//		List<City> citiesList = mapper.readValue(new ClassPathResource(this.env.getProperty(FILE_NAME_CITIES)).getFile(), new TypeReference<List<City>>(){});
		List<City> citiesList = mapper.readValue(new File(this.env.getProperty(FOLDER_HOME) + this.env.getProperty(FILE_NAME_CITIES)), new TypeReference<List<City>>(){});

		citiesList = citiesList.stream().filter(city -> city.getCountry().equalsIgnoreCase(countryCode)).collect(Collectors.toList());
        
        return ResponseEntity.ok(citiesList);
    }
	
	@GetMapping("/list/{countryCode}/{name}")
	@ApiOperation(value = "Filter cities", nickname = "filterCitiesByName")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "List of cities filtered by country code", response = String[].class, responseContainer = "List")
	})
	public ResponseEntity<List<City>> filterCitiesByName(@PathVariable("countryCode") final String countryCode, @PathVariable("name") final String name) throws JsonParseException, JsonMappingException, IOException {
		final ObjectMapper mapper = new ObjectMapper();
//		List<City> citiesList = mapper.readValue(new ClassPathResource(this.env.getProperty(FILE_NAME_CITIES)).getFile(), new TypeReference<List<City>>(){});
		List<City> citiesList = mapper.readValue(new File(this.env.getProperty(FOLDER_HOME) + this.env.getProperty(FILE_NAME_CITIES)), new TypeReference<List<City>>(){});
		
		citiesList = citiesList.stream().filter(city -> city.getCountry().equalsIgnoreCase(countryCode)).collect(Collectors.toList());
		citiesList = citiesList.stream().filter(city -> city.getName().toUpperCase().startsWith(name.toUpperCase())).collect(Collectors.toList());
		
		return ResponseEntity.ok(citiesList);
	}
}
