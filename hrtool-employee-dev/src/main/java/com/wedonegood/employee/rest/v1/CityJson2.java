package com.wedonegood.employee.rest.v1;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Abel Pulido Ponce
 *
 */
public class CityJson2 {

	@JsonProperty
	private String name;
	
	@JsonProperty
	private Map<String, String> divisions;

	public CityJson2() {
		
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
