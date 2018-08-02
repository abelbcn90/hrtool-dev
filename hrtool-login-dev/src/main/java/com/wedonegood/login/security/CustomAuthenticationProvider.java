package com.wedonegood.login.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.wedonegood.common.model.user.UserService;
import com.wedonegood.common.security.UserInfoContextImpl;
import com.wedonegood.login.otp.OTPService;
import com.wedonegood.login.security.exception.WarnBadCredentialsException;

/**
 * 
 * @author Abel Pulido Ponce
 *
 */
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    private UserService userService;

    @Autowired
    private OTPService otpService;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
    	final Authentication auth = super.authenticate(authentication);
    	final UserInfoContextImpl uic = (UserInfoContextImpl) auth.getPrincipal();
        
        try {
            this.otpService.sendCode(uic.getUserId());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return auth;
    }

    @Override
    protected void additionalAuthenticationChecks(final UserDetails userDetails, final UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (null == authentication.getCredentials()) {
            this.logger.debug("Authentication failed: no credentials provided");
            
            throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
            
        } else {
        	final String presentedPassword = authentication.getCredentials().toString();
            
            if (!this.bCryptPasswordEncoder.matches(presentedPassword, userDetails.getPassword())) {
                this.logger.debug("Authentication failed: password does not match stored value");
                
                final int cnt = this.userService.recordLoginFailure((String)authentication.getPrincipal());
                
                if (cnt == 3) {
                    throw new WarnBadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
                }
                
                if (cnt == 4) {
                    throw new LockedException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.locked", "User account is locked"));
                }
                
                throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
            }
        }
    }
}
