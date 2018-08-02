package com.wedonegood.roles.api.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wedonegood.roles.api.model.entity.Client;
import com.wedonegood.roles.api.model.entity.Role;

/**
 * @author Abel Pulido Ponce
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Page<Role> findAllByClientAndActiveIsTrue(Client client, Pageable pageable);
    Page<Role> findAllByActiveIsTrue(Pageable pageable);
	
//    @Query(value = "SELECT r.* FROM role r WHERE r.active = true", nativeQuery = true)
//    List<Role> findRolesByUserId(final long userId);
}
