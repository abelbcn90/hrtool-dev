package com.wedonegood.employee.rest.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Abel Pulido Ponce
 *
 */
public class City {

	@JsonProperty
	private String country;
	
	@JsonProperty
	private String name;
	
	@JsonProperty
	private String lat;
	
	@JsonProperty
	private String lng;
    
    /*
     * Constructors
     */
    
    public City() {
    	
    }
    
    public City(final String country, final String name) {
    	this.country = country;
    	this.name = name;
    }

    /*
	 * Getters & Setters
	 */

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
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
	 * @return the lat
	 */
	public String getLat() {
		return lat;
	}

	/**
	 * @param lat the lat to set
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}

	/**
	 * @return the lng
	 */
	public String getLng() {
		return lng;
	}

	/**
	 * @param lng the lng to set
	 */
	public void setLng(String lng) {
		this.lng = lng;
	}
}
