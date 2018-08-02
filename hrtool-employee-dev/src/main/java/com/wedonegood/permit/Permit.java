package com.wedonegood.permit;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.wedonegood.employee.model.common.BaseEntity;

/**
 * 
 * @author Abel Pulido Ponce
 *
 */
@Entity
public class Permit extends BaseEntity {
	
	@Column(nullable = true)
    private String name;

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
