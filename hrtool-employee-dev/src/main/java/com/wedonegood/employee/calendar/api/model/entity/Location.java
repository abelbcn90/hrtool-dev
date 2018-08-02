package com.wedonegood.employee.calendar.api.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;
    
    public Location() {
    	
    }

    public Location(long id, String name)  {
        this.id = id;
        this.name = name;
    }
}
