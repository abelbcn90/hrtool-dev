package com.wedonegood.employee.rest.v1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

/**
 * @author Abel Pulido Ponce
 *
 */
@Entity
public class Country {

	@Id
	@Size(max = 2)
	@Column(unique = true, nullable = false)
    private String code;
	
	@Column(unique = true, nullable = false)
	private String name;
	
    /*
     * Constructors
     */
    
    public Country() {
    	
    }
    
    public Country(final String code) {
    	this.code = code;
    }
    
    public Country(final String code, final String name) {
    	this.code = code;
    	this.name = name;
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
