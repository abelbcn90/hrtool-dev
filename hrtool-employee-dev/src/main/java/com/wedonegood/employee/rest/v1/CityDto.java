package com.wedonegood.employee.rest.v1;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Abel Pulido Ponce
 *
 */
@ApiModel
public class CityDto {

	@ApiModelProperty(value = "City id", allowEmptyValue = true, example = "432", position = 1, notes = "empty to create new city")
    private Long id;
	
	@ApiModelProperty(value = "City name", position = 2)
	private String name;
	
	@ApiModelProperty(value = "City country", position = 3)
	private CountryDto country;
    
    /*
     * Constructors
     */
    
    public CityDto() {
    	
    }
    
    public CityDto(final City city) {
    	this.id = city.getId();
		this.name = city.getName();
		this.country = new CountryDto(city.getCountry());
    }

    /*
	 * Getters & Setters
	 */

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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

	/**
	 * @return the country
	 */
	public CountryDto getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(CountryDto country) {
		this.country = country;
	}
}
