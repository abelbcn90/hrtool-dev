package com.wedonegood.roles.api.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * @author Abel Pulido Ponce
 *
 */
@Entity
@Table(name="language")
public class Language implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Size(max = 2)
	@Column(name="code", unique = true, nullable = false)
	private String code;
	
	@Size(max = 255)
	@Column(name="description", unique = true, nullable = false)
	private String description;
	
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
