package com.wedonegood.roles.rest.v1.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import com.wedonegood.roles.api.model.entity.Role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class RoleDto {
	
    @ApiModelProperty(value = "Role id", allowEmptyValue = true, example = "432", position = 1, notes = "empty to create new role")
    private Long roleId;
    
    @ApiModelProperty(value = "Role name", position = 2)
    private String name;
    
    @ApiModelProperty(value = "Functions collection", position = 3)
    private Collection<FunctionDto> functions;

    @ApiModelProperty(readOnly = true, value = "Active flag", position = 100)
    private boolean active;
    
    @ApiModelProperty(readOnly = true, value = "Created by user", example = "John Doe", position = 101)
    private String createdBy;
    
    @ApiModelProperty(readOnly = true, value = "Creation date", position = 102)
    private LocalDateTime createdDate;
    
    @ApiModelProperty(readOnly = true, value = "Modified by user", example = "Jane Doe", position = 103)
    private String modifiedBy;
    
    @ApiModelProperty(readOnly = true, value = "Modification date", position = 104)
    private LocalDateTime modifiedDate;

    /*
     * Constructors
     */
    
    public RoleDto() {
    }
    
    public RoleDto(final Long roleId) {
    	this.roleId = roleId;
    }

    public RoleDto(final Role role) {
    	this.roleId = role.getId();
    	this.name = role.getName();
    	
    	if (role.getFunctions() != null && !role.getFunctions().isEmpty()) {
    		this.functions = new ArrayList<>();
            role.getFunctions().forEach(f -> this.functions.add(new FunctionDto(f)));
        }
    	
        this.active = role.isActive();
        this.createdDate = role.getCreatedDate();
        this.createdBy = role.getCreatedBy();
        this.modifiedDate = role.getModifiedDate();
        this.modifiedBy = role.getModifiedBy();
    }
    
    /*
     * Getters & Setters
     */

	/**
	 * @return the roleId
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
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
	 * @return the functions
	 */
	public Collection<FunctionDto> getFunctions() {
		return functions;
	}

	/**
	 * @param functions the functions to set
	 */
	public void setFunctions(Collection<FunctionDto> functions) {
		this.functions = functions;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdDate
	 */
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the modifiedBy
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the modifiedDate
	 */
	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}
