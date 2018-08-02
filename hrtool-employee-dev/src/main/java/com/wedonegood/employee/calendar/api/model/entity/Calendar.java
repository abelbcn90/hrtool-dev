package com.wedonegood.employee.calendar.api.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.wedonegood.common.client.Client;
import com.wedonegood.employee.model.common.BaseEntity;

@Entity
public class Calendar extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer year;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Location location;

    public Calendar() {
    	
    }
    
    public Calendar(String name, Integer year, Client client, Location location) {
        this.year = year;
        this.name = name;
        this.client = client;
        this.location = location;
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
