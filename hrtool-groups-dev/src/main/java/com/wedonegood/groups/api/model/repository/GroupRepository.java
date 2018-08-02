package com.wedonegood.groups.api.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wedonegood.groups.api.model.entity.Groups;
import com.wedonegood.groups.client.entity.Client;

/**
 * @author Abel Pulido Ponce
 *
 */
@Repository
public interface GroupRepository extends JpaRepository<Groups, Long> {
	Page<Groups> findAllByClientAndActiveIsTrue(final Client client, final Pageable pageable);
    Page<Groups> findAllByActiveIsTrue(final Pageable pageable);
    List<Groups> findAllByClientAndActiveIsTrue(final Client client);
    
    @Query(value = "SELECT COUNT(e.id)" +
    		"  FROM employee e" +
    		"  WHERE e.group_id = ?",
			nativeQuery = true)
    Integer findNumberOfEmployeesByGroup(final long groupId);
}
