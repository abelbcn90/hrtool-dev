package com.wedonegood.roles.api.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wedonegood.roles.api.model.entity.Role;

/**
 * @author Abel Pulido Ponce
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Page<Role> findAllByClientIdAndActiveIsTrue(Long client, Pageable pageable);
    Page<Role> findAllByActiveIsTrue(Pageable pageable);
    List<Role> findAllByClientIdAndActiveIsTrue(final Long clientId);
	
    @Query(value = "SELECT DISTINCT r.* " +
			" FROM role r " +
			" LEFT JOIN user_role ur ON ur.role_id = r.id " +
			" WHERE ur.user_id = ? " +
			" AND ur.active = true ",
			nativeQuery = true)
	List<Role> findRolesFromUserRoleByUserIdAndActiveIsTrue(final Long userId);
}
