package com.wedonegood.login.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.wedonegood.common.security.RoleEnum;

public class UiUtils {

    public static boolean hasRole(String role) {
        if(!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || auth.getAuthorities() == null || auth.getAuthorities().size() ==0) {
            return false;
        }
        for(GrantedAuthority ga : auth.getAuthorities()) {
            if(ga.getAuthority().equalsIgnoreCase(role)) {
                return true;
            }
        }
        return false;
    }

    public static void grantAuthority(RoleEnum... roles) {
        changeAuthority(roles, null);
    }

    public static void removeAuthority(RoleEnum... roles) {
        changeAuthority(null, roles);
    }

    public static void changeAuthority(RoleEnum[] grant, RoleEnum[] remove) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> authorities = new ArrayList<>(auth.getAuthorities());
        if(remove != null && remove.length > 0) {
            for(RoleEnum r : remove) {
                for (GrantedAuthority a : authorities) {
                    if (r.getAuthority().equals(a.getAuthority())) {
                        authorities.remove(a);
                        break;
                    }
                }
            }
        }
        if(grant != null && grant.length > 0) {
            for (RoleEnum role : grant) {
                authorities.add(role.getGrantedAuthority());
            }
        }
        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), authorities);
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
}
