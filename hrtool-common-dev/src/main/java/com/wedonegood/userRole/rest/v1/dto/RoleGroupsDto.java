package com.wedonegood.userRole.rest.v1.dto;

import java.util.ArrayList;
import java.util.List;

import com.wedonegood.groups.api.model.entity.Groups;
import com.wedonegood.groups.rest.v1.dto.GroupDto;
import com.wedonegood.roles.api.model.entity.Role;
import com.wedonegood.roles.rest.v1.dto.RoleDto;

/**
 * 
 * @author Abel Pulido Ponce
 *
 */
public class RoleGroupsDto {
	
	private RoleDto role;
	private List<GroupDto> groups;
	
	/*
	 * Contructors
	 */
	
	public RoleGroupsDto() {
		
	}
	
	public RoleGroupsDto(final Role role, final List<Groups> groups) {
		this.role = new RoleDto(role);
		this.groups = new ArrayList<GroupDto>();
		
		for (final Groups g : groups) {
			this.groups.add(new GroupDto(g));
		}
	}
	
	/*
	 * Getters & Setters
	 */
	
	/**
	 * @return the role
	 */
	public RoleDto getRole() {
		return role;
	}
	
	/**
	 * @param role the role to set
	 */
	public void setRole(RoleDto role) {
		this.role = role;
	}

	/**
	 * @return the groups
	 */
	public List<GroupDto> getGroups() {
		return groups;
	}

	/**
	 * @param groups the groups to set
	 */
	public void setGroups(List<GroupDto> groups) {
		this.groups = groups;
	}
}
