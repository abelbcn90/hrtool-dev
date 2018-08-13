package com.wedonegood.userRole;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {
	
	@Autowired
    private UserRoleRepository userRoleRepository;
	
	@Override
	public List<UserRole> getUserRoles() {
		return this.userRoleRepository.findAll();
	}
	
	@Override
	public List<UserRole> findAllByUserIdAndActiveIsTrue(final Long userId) {
		return this.userRoleRepository.findAllByUserIdAndActiveIsTrue(userId);
	}
	
	@Override
	public List<UserRole> findUserRolesByUserId(final Long userId) {
		return this.userRoleRepository.findAllByUserId(userId);
	}
	
	@Override
    public UserRole get(final Long bilingId) {
        return this.userRoleRepository.getOne(bilingId);
    }
	
	@Override
	public List<UserRole> saveAll(final List<UserRole> userRoles) {
		for (final UserRole userRole : userRoles) {
			userRole.addAudit();
		}
		
		return this.userRoleRepository.saveAll(userRoles);
	}
}
