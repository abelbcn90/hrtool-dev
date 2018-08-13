package com.wedonegood.roles.rest.v1.dto;

import com.wedonegood.roles.api.model.entity.Function;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author Abel Pulido Ponce
 *
 */
@ApiModel
public class FunctionDto {
	
    @ApiModelProperty(value = "Function id", allowEmptyValue = true, example = "432", position = 1, notes = "empty to create new function")
    private Long functionId;
    
    @ApiModelProperty(readOnly = true, value = "Function name", position = 100)
    private String name;
    
    @ApiModelProperty(readOnly = true, value = "Function description", position = 100)
    private String description;
    
    /*
     * Contructors
     */
    
    public FunctionDto() {
    }
    
    public FunctionDto(final Function function) {
    	this.functionId = function.getId();
    	this.name = function.getName();
    	this.description = function.getDescription();
    }
    
    /*
     * Getters & Setters
     */

	/**
	 * @return the functionId
	 */
	public Long getFunctionId() {
		return functionId;
	}

	/**
	 * @param functionId the functionId to set
	 */
	public void setFunctionId(Long functionId) {
		this.functionId = functionId;
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
