package com.wedonegood.login.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.wedonegood.employee.user.api.model.entity.User;

public class UserInfoContextImpl implements UserInfoContext {

    private Long userId;
    private String userEmail;
    private String userFirstName;
    private String userLastName;
    private String userPhone;
    private String userPassword;

    private int userAttemptCnt;
    
    private List<GrantedAuthority> authorities = new ArrayList<>();

    public UserInfoContextImpl(User user, RoleEnum... roles) {
        this.userId = user.getUserId();
        this.userEmail = user.getUserEmail();
        this.userFirstName = user.getUserFirstName();
        this.userLastName = user.getUserLastName();
        this.userPhone = user.getUserPhone();
        this.userPassword = user.getUserPassword();

        this.userAttemptCnt = user.getUserAttemptCnt();

        if(roles != null && roles.length > 0) {
            for(RoleEnum role : roles) {
                addRole(role);
            }
        }

        if(user.isUserChangePwd()) {
            addRole(RoleEnum.CHANGE_PASSWORD);
        }
        
//        for (final Role role : user.getRoles()) {
//        	for (final Function function : role.getFunctions()) {
//        		final SimpleGrantedAuthority functionAuthority = new SimpleGrantedAuthority(function.getName());
//        		if (!this.authorities.contains(functionAuthority)) {
//        			this.authorities.add(functionAuthority);
//        		}
//        	}
//        }
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
}
