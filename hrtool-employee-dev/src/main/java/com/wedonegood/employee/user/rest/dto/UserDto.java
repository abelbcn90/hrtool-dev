package com.wedonegood.employee.user.rest.dto;

import com.wedonegood.employee.user.api.model.entity.User;

import io.swagger.annotations.ApiModel;

@ApiModel("User")
public class UserDto {
    private Long userId;
    private String userEmail;
    private String userFirstName;
    private String userLastName;
    private String userPhone;

    public UserDto() {
    }
    
    public UserDto(User user) {
        this.userId = user.getUserId();
        this.userEmail = user.getUserEmail();
        this.userFirstName = user.getUserFirstName();
        this.userLastName = user.getUserLastName();
        this.userPhone = user.getUserPhone();
    }

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * @return the userFirstName
	 */
	public String getUserFirstName() {
		return userFirstName;
	}

	/**
	 * @param userFirstName the userFirstName to set
	 */
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	/**
	 * @return the userLastName
	 */
	public String getUserLastName() {
		return userLastName;
	}

	/**
	 * @param userLastName the userLastName to set
	 */
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	/**
	 * @return the userPhone
	 */
	public String getUserPhone() {
		return userPhone;
	}

	/**
	 * @param userPhone the userPhone to set
	 */
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
    
    
}
