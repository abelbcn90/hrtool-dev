package com.wedonegood.groups.rest.v1.dto;

import java.time.LocalDateTime;

import com.wedonegood.groups.api.model.entity.Groups;
import com.wedonegood.groups.calendar.rest.v1.dto.CalendarDto;
import com.wedonegood.groups.calendar.working.hours.rest.dto.WorkingHoursDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GroupDto {
	
    @ApiModelProperty(value = "Group id", allowEmptyValue = true, example = "432", position = 1, notes = "empty to create new group")
    private Long groupId;
    
    @ApiModelProperty(value = "Group name", position = 2)
    private String name;
    
    @ApiModelProperty(value = "Group client", position = 3)
    private Long clientId;
    
    @ApiModelProperty(value = "Group working hours", position = 4)
    private WorkingHoursDto workingHours;
    
    @ApiModelProperty(value = "Group calendar", position = 5)
    private CalendarDto calendar;
    
    @ApiModelProperty(value = "Number of employees by Group", position = 6)
    private Integer employeesNumber;
    
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
    
    public GroupDto() {
    	
    }
    
    public GroupDto(final Long groupId) {
    	this.groupId = groupId;
    }
    
    public GroupDto(final Groups group) {
    	this.groupId = group.getId();
    	this.name = group.getName();
    	this.clientId = group.getClient().getId();
    	this.workingHours = new WorkingHoursDto(group.getWorkingHours());
    	this.calendar = new CalendarDto(group.getCalendar());
    	
    	this.employeesNumber = group.getEmployeesNumber();
    	
        this.active = group.isActive();
        this.createdDate = group.getCreatedDate();
        this.createdBy = group.getCreatedBy();
        this.modifiedDate = group.getModifiedDate();
        this.modifiedBy = group.getModifiedBy();
    }
    
    /*
     * Getters & Setters
     */

    /**
	 * @return the groupId
	 */
	public Long getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
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
	 * @return the clientId
	 */
	public Long getClientId() {
		return clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the workingHours
	 */
	public WorkingHoursDto getWorkingHours() {
		return workingHours;
	}

	/**
	 * @param workingHours the workingHours to set
	 */
	public void setWorkingHours(WorkingHoursDto workingHours) {
		this.workingHours = workingHours;
	}

	/**
	 * @return the calendar
	 */
	public CalendarDto getCalendar() {
		return calendar;
	}

	/**
	 * @param calendar the calendar to set
	 */
	public void setCalendar(CalendarDto calendar) {
		this.calendar = calendar;
	}

	/**
	 * @return the employeesNumber
	 */
	public Integer getEmployeesNumber() {
		return employeesNumber;
	}

	/**
	 * @param employeesNumber the employeesNumber to set
	 */
	public void setEmployeesNumber(Integer employeesNumber) {
		this.employeesNumber = employeesNumber;
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
