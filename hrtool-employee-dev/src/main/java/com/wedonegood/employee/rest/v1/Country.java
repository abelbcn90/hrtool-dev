package com.wedonegood.employee.rest.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Abel Pulido Ponce
 *
 */
public class Country {

	@JsonProperty
	private String name;
	
	@JsonProperty(value = "alpha-2")
	private String alpha2;
	
	@JsonProperty(value = "alpha-3")
	private String alpha3;
	
	@JsonProperty(value = "country-code")
	private String countryCode;
	
	@JsonProperty(value = "iso_3166-2")
	private String iso_3166_2;
	
	@JsonProperty
	private String region;
	
	@JsonProperty(value = "sub-region")
	private String subRegion;
	
	@JsonProperty(value = "intermediate-region")
	private String intermediateRegion;
	
	@JsonProperty(value = "region-code")
	private String regionCode;
	
	@JsonProperty(value = "sub-region-code")
	private String subRegionCode;
	
	@JsonProperty(value = "intermediate-region-code")
	private String intermediateRegionCode;
	
	
    /*
     * Constructors
     */
    
    public Country() {
    	
    }
    
    /*
	 * Getters & Setters
	 */

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
	 * @return the alpha2
	 */
	public String getAlpha2() {
		return alpha2;
	}

	/**
	 * @param alpha2 the alpha2 to set
	 */
	public void setAlpha2(String alpha2) {
		this.alpha2 = alpha2;
	}

	/**
	 * @return the alpha3
	 */
	public String getAlpha3() {
		return alpha3;
	}

	/**
	 * @param alpha3 the alpha3 to set
	 */
	public void setAlpha3(String alpha3) {
		this.alpha3 = alpha3;
	}

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
	 * @return the iso_3166_2
	 */
	public String getIso_3166_2() {
		return iso_3166_2;
	}

	/**
	 * @param iso_3166_2 the iso_3166_2 to set
	 */
	public void setIso_3166_2(String iso_3166_2) {
		this.iso_3166_2 = iso_3166_2;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * @return the subRegion
	 */
	public String getSubRegion() {
		return subRegion;
	}

	/**
	 * @param subRegion the subRegion to set
	 */
	public void setSubRegion(String subRegion) {
		this.subRegion = subRegion;
	}

	/**
	 * @return the intermediateRegion
	 */
	public String getIntermediateRegion() {
		return intermediateRegion;
	}

	/**
	 * @param intermediateRegion the intermediateRegion to set
	 */
	public void setIntermediateRegion(String intermediateRegion) {
		this.intermediateRegion = intermediateRegion;
	}

	/**
	 * @return the regionCode
	 */
	public String getRegionCode() {
		return regionCode;
	}

	/**
	 * @param regionCode the regionCode to set
	 */
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	/**
	 * @return the subRegionCode
	 */
	public String getSubRegionCode() {
		return subRegionCode;
	}

	/**
	 * @param subRegionCode the subRegionCode to set
	 */
	public void setSubRegionCode(String subRegionCode) {
		this.subRegionCode = subRegionCode;
	}

	/**
	 * @return the intermediateRegionCode
	 */
	public String getIntermediateRegionCode() {
		return intermediateRegionCode;
	}

	/**
	 * @param intermediateRegionCode the intermediateRegionCode to set
	 */
	public void setIntermediateRegionCode(String intermediateRegionCode) {
		this.intermediateRegionCode = intermediateRegionCode;
	}
}
