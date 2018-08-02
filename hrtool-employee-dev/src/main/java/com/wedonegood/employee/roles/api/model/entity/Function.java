package com.wedonegood.employee.roles.api.model.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Abel Pulido Ponce
 *
 */
@Entity
@Table(name="function")
public class Function implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
    
    @Column(unique = true)
    private String description;
    
    /*
     * Constructors
     */
    
    public Function() {
    }
    
    public Function(final Long id, final String name) {
    	this.id = id;
    	this.name = name;
    }

    /*
	 * Getters & Setters
	 */

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
