package com.wedonegood.common.language;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Abel Pulido Ponce
 *
 */
@ApiModel
public class LanguageDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "Language code", allowEmptyValue = true, example = "EN", position = 1)
	private String code;
	
	@ApiModelProperty(value = "Language description", position = 2)
	private String description;
	
	public LanguageDto() {
		
	}
	
	public LanguageDto(final String code) {
		this.code = code;
	}
	
	public LanguageDto(final Language language) {
		this.code = language.getCode();
		this.description = language.getDescription();
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
