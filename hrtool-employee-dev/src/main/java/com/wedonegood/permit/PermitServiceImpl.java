package com.wedonegood.permit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermitServiceImpl implements PermitService {
	
	@Autowired
    private PermitRepository permitRepository;
	
	@Override
	public List<Permit> getPermits() {
		return this.permitRepository.findAll();
	}
	
	@Override
    public Permit get(final Long roleId) {
        return this.permitRepository.getOne(roleId);
    }
}
