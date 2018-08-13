package com.wedonegood.groups.api.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wedonegood.groups.api.model.entity.Groups;

/**
 * @author Abel Pulido Ponce
 *
 */
@Repository
public interface GroupRepository extends JpaRepository<Groups, Long> {
	Page<Groups> findAllByClientIdAndActiveIsTrue(final Long client, final Pageable pageable);
	List<Groups> findAllByClientIdAndActiveIsTrue(final Long clientId);
    Page<Groups> findAllByActiveIsTrue(final Pageable pageable);
    
    @Query(value = "SELECT COUNT(e.id)" +
    		"  FROM employee e" +
    		"  WHERE e.group_id = ?",
			nativeQuery = true)
    Integer findNumberOfEmployeesByGroup(final long groupId);
    
    @Query(value = "SELECT g.* " +
    		" FROM groups g " +
			" LEFT JOIN user_role ur ON ur.group_id = g.id " +
    		" WHERE ur.user_id = ?1 " +
    		" AND ur.role_id = ?2 " +
			" AND ur.active = true ",
			nativeQuery = true)
    List<Groups> findGroupsFromUserRoleByUserIdAndRoleIdAndActiveIsTrue(final Long userId, final Long roleId);
}
