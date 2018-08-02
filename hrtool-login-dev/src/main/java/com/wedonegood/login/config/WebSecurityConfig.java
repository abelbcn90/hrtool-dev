package com.wedonegood.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.wedonegood.login.security.CustomAuthenticationFailureHandler;
import com.wedonegood.login.security.CustomAuthenticationProvider;
import com.wedonegood.login.security.CustomAuthenticationService;
import com.wedonegood.login.security.CustomUserDetailsChecker;
import com.wedonegood.login.security.RoleEnum;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CustomAuthenticationFailureHandler authenticationFailureHandler;

    @Bean(name = "userDetailService")
    @Override
    public UserDetailsService userDetailsService() {
        return new CustomAuthenticationService();
    }

    @Bean
    public DaoAuthenticationProvider getLimitedAuthenticationProvider() {
        final DaoAuthenticationProvider authProvider = new CustomAuthenticationProvider();
        authProvider.setPasswordEncoder(this.bCryptPasswordEncoder);
        authProvider.setUserDetailsService(userDetailsService());
//        ReflectionSaltSource saltSource = new ReflectionSaltSource();
//        saltSource.setUserPropertyToUse("getUserId");
//        authProvider.setSaltSource(saltSource);
        authProvider.setPostAuthenticationChecks(new CustomUserDetailsChecker());
        
        return authProvider;
    }

    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth, final ApplicationContext context) throws Exception {
        auth.authenticationProvider(getLimitedAuthenticationProvider());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable();

        http
	        .authorizeRequests()
	            .antMatchers("/", "/login", "/login/do", "/login-error", "/login-warn", "/login-locked", "/login-no-phone").permitAll()
	            .antMatchers("/login/code", "/login/code/check", "/login/code-error", "/login/code-warn").hasRole(RoleEnum.PRE_AUTH_USER.getRole())
	            .antMatchers("/pwd-change").hasRole(RoleEnum.CHANGE_PASSWORD.getRole())
	            .anyRequest().hasRole(RoleEnum.USER.getRole())
	            .and()
	        .formLogin()
	            .loginPage("/login")
	            .loginProcessingUrl("/login/do")
	            .usernameParameter("username")
	            .passwordParameter("password")
	            .defaultSuccessUrl("/login/code", true)
	            .failureHandler(authenticationFailureHandler)
	            .and()
	        .logout()
	            .invalidateHttpSession(true)
	            .logoutUrl("/logout")
	            .logoutSuccessUrl("/")
	            .permitAll()
	            .and()
	        .sessionManagement()
                .sessionFixation()
                    .newSession()
                    .maximumSessions(1)
                    .maxSessionsPreventsLogin(true);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
