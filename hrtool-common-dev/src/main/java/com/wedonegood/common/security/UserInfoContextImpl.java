package com.wedonegood.common.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.wedonegood.common.user.api.model.entity.User;

public class UserInfoContextImpl implements UserInfoContext {

    private Long userId;
    private String userEmail;
    private String userFirstName;
    private String userLastName;
    private String userPhone;
    private String userPassword;
    private Long clientId;
    private String languageCode;
    private String profilePicture;

    private int userAttemptCnt;
    
    private List<GrantedAuthority> authorities = new ArrayList<>();

    public UserInfoContextImpl(User user, RoleEnum... roles) {
        this.userId = user.getUserId();
        this.userEmail = user.getUserEmail();
        this.userFirstName = user.getUserFirstName();
        this.userLastName = user.getUserLastName();
        this.userPhone = user.getUserPhone();
        this.userPassword = user.getUserPassword();
        this.clientId = user.getClientId();
        this.languageCode = user.getLanguageCode();
        this.profilePicture = user.getProfilePicture();

        this.userAttemptCnt = user.getUserAttemptCnt();

        if(roles != null && roles.length > 0) {
            for(RoleEnum role : roles) {
                addRole(role);
            }
        }

        if(user.isUserChangePwd()) {
            addRole(RoleEnum.CHANGE_PASSWORD);
        }
    }

    public boolean addRole(RoleEnum role) {
        if(!hasRole(role)) {
            authorities.add(role.getGrantedAuthority());
            return true;
        }
        return false;
    }

    public boolean hasRole(RoleEnum role) {
        for(GrantedAuthority a : authorities) {
            if(role.getAuthority().equals(a.getAuthority())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return getUserEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.userAttemptCnt < 4;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public String getUserEmail() {
        return userEmail;
    }

    @Override
    public String getUserFirstName() {
        return userFirstName;
    }

    @Override
    public String getUserLastName() {
        return userLastName;
    }

    @Override
    public String getUserPhone() {
        return userPhone;
    }

    public int getUserAttemptCnt() {
        return userAttemptCnt;
    }

	@Override
	public Long getClientId() {
		return this.clientId;
	}

	@Override
	public String getLanguageCode() {
		return this.languageCode;
	}
	
	@Override
	public String getProfilePicture() {
		return this.profilePicture;
	}
}
