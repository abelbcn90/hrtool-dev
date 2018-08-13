package com.wedonegood.common.client;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ClientDto {
	
    @ApiModelProperty(value = "Client id", allowEmptyValue = true, example = "432", position = 1, notes = "empty to create new client")
    private Long clientId;
    
    @ApiModelProperty(value = "Role name", position = 2)
    private String name;
    
    @ApiModelProperty(value = "Client logo", position = 3)
    private String logo;
    
    @ApiModelProperty(value = "Client color 1", position = 4)
    private String color1;
    
    @ApiModelProperty(value = "Client color 2", position = 5)
    private String color2;
    
    @ApiModelProperty(value = "Client color 3", position = 6)
    private String color3;
    
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
    
    public ClientDto() {
    }

    public ClientDto(final Client client) {
    	this.clientId = client.getId();
    	this.name = client.getName();
    	this.logo = client.getLogo();
    	this.color1 = client.getColor1();
    	this.color2 = client.getColor2();
    	this.color3 = client.getColor3();    	
    	
        this.active = client.isActive();
        this.createdDate = client.getCreatedDate();
        this.createdBy = client.getCreatedBy();
        this.modifiedDate = client.getModifiedDate();
        this.modifiedBy = client.getModifiedBy();
    }
    
    /*
     * Getters & Setters
     */

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

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
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
