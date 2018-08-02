package com.wedonegood.common.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum RoleEnum {
    USER("USER"),
    PRE_AUTH_USER("PRE_AUTH_USER"),
    CHANGE_PASSWORD("CHANGE_PASSWORD"),
	
	// Functions
	ROLES_MANAGEMENT("ROLES_MANAGEMENT"),
	GROUPS_MANAGEMENT("GROUPS_MANAGEMENT"),
	WORKING_HOURS_MANAGEMENT("WORKING_HOURS_MANAGEMENT"),
	CALENDAR_MANAGEMENT("CALENDAR_MANAGEMENT"),
	EMPLOYEES_VISUALIZATION("EMPLOYEES_VISUALIZATION"),
	EMPLOYEES_EDITION("EMPLOYEES_EDITION"),
	EMPLOYEES_REMOVAL("EMPLOYEES_REMOVAL"),
	TIMESHEET_VISUALIZATION("TIMESHEET_VISUALIZATION"),
	TIMESHEET_EDITION("TIMESHEET_EDITION"),
	TIMESHEET_REMOVAL("TIMESHEET_REMOVAL"),
	TIMESHEET_APPROVAL("TIMESHEET_APPROVAL"),
	HOLIDAYS_APPROVAL("HOLIDAYS_APPROVAL");

    private String authority;
    private String role;

    RoleEnum(String role) {
        this.authority = "ROLE_" + role;
        this.role = role;
    }

    public String getAuthority() {
        return authority;
    }

    public String getRole() {
        return role;
    }

    public GrantedAuthority getGrantedAuthority() {
        return new SimpleGrantedAuthority(authority);
    }


}
