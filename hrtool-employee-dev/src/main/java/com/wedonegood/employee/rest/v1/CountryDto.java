package com.wedonegood.employee.rest.v1;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Abel Pulido Ponce
 *
 */
@ApiModel
public class CountryDto {

	@ApiModelProperty(value = "Country code", allowEmptyValue = true, example = "ES", position = 1, notes = "empty to create new country")
    private String countryCode;
	
	@ApiModelProperty(value = "Country name", position = 2)
	private String name;
	
    /*
     * Constructors
     */
    
    public CountryDto() {
    	
    }
    
    public CountryDto(final String countryCode) {
    	this.countryCode = countryCode;
    }
    
    public CountryDto(final Country country) {
    	this.countryCode = country.getCode();
    	this.name = country.getName();
    }
    
    /*
	 * Getters & Setters
	 */

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
