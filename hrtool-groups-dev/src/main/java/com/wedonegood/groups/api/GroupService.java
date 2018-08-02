package com.wedonegood.groups.api;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wedonegood.groups.api.model.entity.Groups;
import com.wedonegood.groups.client.entity.Client;

/**
 * @author Abel Pulido Ponce
 *
 */
public interface GroupService {
	Page<Groups> getGroups(final Pageable pageable);
	Page<Groups> getGroups(final Client client, final Pageable pageable);
    Groups save(final Groups group);
    Groups get(final long groupId);
    List<Groups> list();
    List<Groups> listAllByClient(final Client client);
    boolean delete(final Long groupId);
    
    Integer findNumberOfEmployeesByGroup(final long groupId);
}
