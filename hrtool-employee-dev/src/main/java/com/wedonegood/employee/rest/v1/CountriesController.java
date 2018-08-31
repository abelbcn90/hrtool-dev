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
@RequestMapping(value = "/api/v1/countries")
@Api(value="Country", description="Operations pertaining to Countries", position = 3)
public class CountriesController {
	
	private final static String FOLDER_HOME = "folder.home";
	private final static String FILE_NAME_COUNTRIES = "file.name.countries";
	
	@Autowired
	private Environment env;
	
	@GetMapping("/list")
	@ApiOperation(value = "Get countries", nickname = "listCountries")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "List of countries", response = String[].class, responseContainer = "List")
	})
	public ResponseEntity<List<Country>> listCountries() throws JsonParseException, JsonMappingException, IOException {
		final ObjectMapper mapper = new ObjectMapper();
//		final List<Country> countriesList = mapper.readValue(new ClassPathResource(this.env.getProperty(FILE_NAME_COUNTRIES)).getFile(), new TypeReference<List<Country>>(){});
		final List<Country> countriesList = mapper.readValue(new File(this.env.getProperty(FOLDER_HOME) + this.env.getProperty(FILE_NAME_COUNTRIES)), new TypeReference<List<Country>>(){});
		
		return ResponseEntity.ok(countriesList);
	}
	
	@GetMapping("/list/{name}")
    @ApiOperation(value = "Filter countries", nickname = "filterCountries")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of countries filtered by name", response = String[].class, responseContainer = "List")
    })
    public ResponseEntity<List<Country>> filterCountries(@PathVariable("name") final String name) throws JsonParseException, JsonMappingException, IOException {
		final ObjectMapper mapper = new ObjectMapper();
//		List<Country> countriesList = mapper.readValue(new ClassPathResource(this.env.getProperty(FILE_NAME_COUNTRIES)).getFile(), new TypeReference<List<Country>>(){});
		List<Country> countriesList = mapper.readValue(new File(this.env.getProperty(FOLDER_HOME) + this.env.getProperty(FILE_NAME_COUNTRIES)), new TypeReference<List<Country>>(){});

		countriesList = countriesList.stream().filter(country -> country.getName().toUpperCase().startsWith(name.toUpperCase())).collect(Collectors.toList());
		
        return ResponseEntity.ok(countriesList);
    }
}
