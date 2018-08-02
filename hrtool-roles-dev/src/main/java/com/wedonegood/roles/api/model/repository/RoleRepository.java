package com.wedonegood.roles.api.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wedonegood.common.client.Client;
import com.wedonegood.roles.api.model.entity.Role;

/**
 * @author Abel Pulido Ponce
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Page<Role> findAllByClientAndActiveIsTrue(Client client, Pageable pageable);
    Page<Role> findAllByActiveIsTrue(Pageable pageable);
    List<Role> findAllByClientAndActiveIsTrue(Client client);
	
//    @Query(value = "SELECT r.* FROM role r WHERE r.active = true", nativeQuery = true)
//    List<Role> findRolesByUserId(final long userId);
}
