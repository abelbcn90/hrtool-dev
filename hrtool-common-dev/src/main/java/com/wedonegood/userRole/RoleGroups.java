package com.wedonegood.userRole;

import java.util.List;

import com.wedonegood.groups.api.model.entity.Groups;
import com.wedonegood.roles.api.model.entity.Role;

/**
 * 
 * @author Abel Pulido Ponce
 *
 */
public class RoleGroups {
	
	private Role role;
	private List<Groups> groups;
	
	/*
	 * Contructors
	 */
	
	public RoleGroups() {
		
	}
	
	public RoleGroups(final Role role, final List<Groups> groups) {
		this.role = role;
		this.groups = groups;
	}
	
	/*
	 * Getters & Setters
	 */
	
	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return the groups
	 */
	public List<Groups> getGroups() {
		return groups;
	}

	/**
	 * @param groups the groups to set
	 */
	public void setGroups(List<Groups> groups) {
		this.groups = groups;
	}
}
