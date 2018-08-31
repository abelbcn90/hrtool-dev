package com.wedonegood.groups.api;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wedonegood.groups.api.model.entity.Groups;
import com.wedonegood.groups.api.model.repository.GroupRepository;

/**
 * 
 * @author Abel Pulido Ponce
 *
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;
    
    public Page<Groups> getGroups(final Pageable pageable) {
        return this.groupRepository.findAllByActiveIsTrue(pageable);
    }

    public Page<Groups> getGroups(final Long client, final Pageable pageable) {
        return this.groupRepository.findAllByClientIdAndActiveIsTrue(client, pageable);
    }
    
    
    @Override
    @Transactional
    public Groups save(final Groups group) {
    	group.addAudit();
    	
        return this.groupRepository.saveAndFlush(group);
    }
    
    @Override
    public Groups get(final long groupId) {
        return this.groupRepository.findById(groupId).get();
    }
    
    @Override
    public List<Groups> list() {
        return this.groupRepository.findAll();
    }
    
    @Override
    public List<Groups> listAllByClientId(final Long clientId) {
    	return this.groupRepository.findAllByClientIdAndActiveIsTrueOrderByIdDesc(clientId);
    }
    
    @Override
    @Transactional
	public boolean delete(final Long groupId) {
		final Groups group = this.get(groupId);
        
		if (null == group) {
            return false;
        }
		
        group.setActive(false);
        
        this.save(group);
        
        return true;
	}
    
    @Override
    public Integer findNumberOfEmployeesByGroup(final long groupId) {
    	final Integer result = this.groupRepository.findNumberOfEmployeesByGroup(groupId);
    	
    	return null != result ? result : 0;
    }
    
    @Override
    public List<Groups> findGroupsFromUserRoleByUserIdAndRoleIdAndActiveIsTrue(final Long userId, final Long roleId) {
    	return this.groupRepository.findGroupsFromUserRoleByUserIdAndRoleIdAndActiveIsTrue(userId, roleId);
    }
}
