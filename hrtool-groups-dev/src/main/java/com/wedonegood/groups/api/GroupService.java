package com.wedonegood.groups.api;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wedonegood.groups.api.model.entity.Groups;

/**
 * @author Abel Pulido Ponce
 *
 */
public interface GroupService {
	Page<Groups> getGroups(final Pageable pageable);
	Page<Groups> getGroups(final Long client, final Pageable pageable);
    Groups save(final Groups group);
    Groups get(final long groupId);
    List<Groups> list();
    List<Groups> listAllByClientId(final Long clientId);
    boolean delete(final Long groupId);
    
    Integer findNumberOfEmployeesByGroup(final long groupId);
    
    List<Groups> findGroupsFromUserRoleByUserIdAndRoleIdAndActiveIsTrue(final Long userId, final Long roleId);
}
