package com.wedonegood.hrtool.auditing;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

/**
 * 
 * @author Abel Pulido Ponce
 *
 */
public class AuditorAwareImpl implements AuditorAware<String> {
  
    @Override
    public Optional<String> getCurrentAuditor() {
    	return Optional.of("admin");
    }
}