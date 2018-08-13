package com.wedonegood.userRole;

import java.util.List;

public interface UserRoleService {
	List<UserRole> getUserRoles();
	List<UserRole> findAllByUserIdAndActiveIsTrue(final Long userId);
	List<UserRole> findUserRolesByUserId(final Long userId);
	UserRole get(final Long userRoleId);
	List<UserRole> saveAll(final List<UserRole> userRoles);
}
