package com.wedonegood.employee.rest.v1;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Abel Pulido Ponce
 *
 */
public class CityJson {

	
	@JsonProperty
	private String af;
	
	@JsonProperty
	private String name;
	
	@JsonProperty
	private Map<String, String> divisions;
	
	public CityJson() {
		
	}
	
	

	/**
	 * @return the af
	 */
	public String getAf() {
		return af;
	}



	/**
	 * @param af the af to set
	 */
	public void setAf(String af) {
		this.af = af;
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
	 * @return the divisions
	 */
	public Map<String, String> getDivisions() {
		return divisions;
	}

	/**
	 * @param divisions the divisions to set
	 */
	public void setDivisions(Map<String, String> divisions) {
		this.divisions = divisions;
	}
}
