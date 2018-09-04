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
    private String code;
	
	@ApiModelProperty(value = "Country name", position = 2)
	private String name;
	
    /*
     * Constructors
     */
    
    public CountryDto() {
    	
    }
    
    public CountryDto(final Country country) {
    	this.code = country.getCode();
    	this.name = country.getName();
    }
    
    /*
	 * Getters & Setters
	 */

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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
