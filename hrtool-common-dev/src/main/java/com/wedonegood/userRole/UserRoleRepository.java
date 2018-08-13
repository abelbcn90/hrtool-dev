package com.wedonegood.userRole;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	
	List<UserRole> findAllByUserIdAndActiveIsTrue(final Long userId);
	List<UserRole> findAllByUserId(final Long userId);
}
