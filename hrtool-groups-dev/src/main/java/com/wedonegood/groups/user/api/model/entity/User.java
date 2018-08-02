package com.wedonegood.groups.user.api.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.wedonegood.groups.security.common.IUser;

@Entity
public class User implements IUser {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long userId;

    @Column(unique = true, nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String userFirstName;

    @Column(nullable = false)
    private String userLastName;

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false)
    private String userPhone;

    @Column(nullable = false)
    private int userAttemptCnt = 0;

    @Column(nullable = false)
    private boolean userChangePwd;
    
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "user_role",
//	    joinColumns = @JoinColumn(name = "user_id"),
//	    inverseJoinColumns = @JoinColumn(name = "role_id")
//	)
//    private List<Role> roles;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public int getUserAttemptCnt() {
        return userAttemptCnt;
    }

    public void setUserAttemptCnt(int userAttemptCnt) {
        this.userAttemptCnt = userAttemptCnt;
    }

    public boolean isUserChangePwd() {
        return userChangePwd;
    }

    public void setUserChangePwd(boolean userChangePwd) {
        this.userChangePwd = userChangePwd;
    }
    
//    /**
//	 * @return the roles
//	 */
//	public List<Role> getRoles() {
//		return roles;
//	}
//
//	/**
//	 * @param roles the roles to set
//	 */
//	public void setRoles(List<Role> roles) {
//		this.roles = roles;
//	}
}
