package com.wedonegood.groups.api.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wedonegood.calendar.api.model.entity.Calendar;
import com.wedonegood.common.client.Client;
import com.wedonegood.common.model.common.BaseEntity;
import com.wedonegood.working.hours.api.model.entity.WorkingHours;

/**
 * @author Abel Pulido Ponce
 *
 */
@Entity
@Table(name="groups")
public class Groups extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @Column(unique = true, nullable = false)
    private String name;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private WorkingHours workingHours;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Calendar calendar;
    
    @Transient
    private Integer employeesNumber;

    /*
     * Constructors
     */
    
    public Groups() {
    }
    
    public Groups(final String name) {
    	this.name = name;
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
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * @return the workingHours
	 */
	public WorkingHours getWorkingHours() {
		return workingHours;
	}

	/**
	 * @param workingHours the workingHours to set
	 */
	public void setWorkingHours(WorkingHours workingHours) {
		this.workingHours = workingHours;
	}

	/**
	 * @return the calendar
	 */
	public Calendar getCalendar() {
		return calendar;
	}

	/**
	 * @param calendar the calendar to set
	 */
	public void setCalendar(Calendar calendar) {
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
}
