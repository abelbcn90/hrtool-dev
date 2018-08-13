package com.wedonegood.roles.api;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wedonegood.roles.api.model.entity.Role;

/**
 * @author Abel Pulido Ponce
 *
 */
public interface RoleService {
	Page<Role> getRoles(Pageable pageable);
    Page<Role> getRoles(Long client, Pageable pageable);
    Role save(final Role role);
    Role get(final long roleId);
    Role findById(final Long roleId);
    List<Role> list();
    List<Role> findAllByClientIdAndActiveIsTrue(final Long clientId);
//    List<Role> findRolesByUserId(final long userId);
    void delete(final Long roleId);
    List<Role> findRolesFromUserRoleByUserIdAndActiveIsTrue(final Long userId);
}
