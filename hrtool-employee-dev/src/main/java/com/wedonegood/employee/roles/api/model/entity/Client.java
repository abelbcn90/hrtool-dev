package com.wedonegood.employee.roles.api.model.entity;

import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.wedonegood.employee.model.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * @author Abel Pulido Ponce
 *
 */
@Entity
@Audited
@EntityListeners(AuditingEntityListener.class)
@Table(name="client")
//@AttributeOverride(name = "id", column = @Column(name = "client_id", nullable = false))
public class Client extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Size(max = 255)
	@Column(name="name", unique = true, nullable = false)
	private String name;
	
	@Size(max = 255)
	@Column(name="logo", unique = true, nullable = false)
	private String logo;
	
	@Size(max = 7)
	@Column(name="color1", unique = true, nullable = false)
	private String color1;
	
	@Size(max = 7)
	@Column(name="color2", unique = true, nullable = false)
	private String color2;
	
	@Size(max = 7)
	@Column(name="color3", unique = true, nullable = false)
	private String color3;
	
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
	 * @return the logo
	 */
	public String getLogo() {
		return logo;
	}

	/**
	 * @param logo the logo to set
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}

	/**
	 * @return the color1
	 */
	public String getColor1() {
		return color1;
	}

	/**
	 * @param color1 the color1 to set
	 */
	public void setColor1(String color1) {
		this.color1 = color1;
	}

	/**
	 * @return the color2
	 */
	public String getColor2() {
		return color2;
	}

	/**
	 * @param color2 the color2 to set
	 */
	public void setColor2(String color2) {
		this.color2 = color2;
	}

	/**
	 * @return the color3
	 */
	public String getColor3() {
		return color3;
	}

	/**
	 * @param color3 the color3 to set
	 */
	public void setColor3(String color3) {
		this.color3 = color3;
	}
}
