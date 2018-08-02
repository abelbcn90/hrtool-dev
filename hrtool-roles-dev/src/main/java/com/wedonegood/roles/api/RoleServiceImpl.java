package com.wedonegood.roles.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wedonegood.common.client.Client;
import com.wedonegood.roles.api.model.entity.Role;
import com.wedonegood.roles.api.model.repository.RoleRepository;

/**
 * 
 * @author Abel Pulido Ponce
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    
    @Override
    public Page<Role> getRoles(Pageable pageable) {
        return this.roleRepository.findAllByActiveIsTrue(pageable);
    }

    @Override
    public Page<Role> getRoles(Client client, Pageable pageable) {
        return this.roleRepository.findAllByClientAndActiveIsTrue(client, pageable);
    }
    
    @Override
    public Role save(final Role role) {
        this.validate(role);
        role.addAudit();
        
        return this.roleRepository.saveAndFlush(role);
    }
    
    @Override
    public Role get(final long roleId) {
        return this.roleRepository.getOne(roleId);
    }
    
    @Override
    public List<Role> list() {
        return this.roleRepository.findAll();
    }
    
    @Override
    public List<Role> findAllByClientAndActiveIsTrue(final Client client) {
    	return this.roleRepository.findAllByClientAndActiveIsTrue(client);
    }
    
//    @Override
//	public List<Role> findRolesByUserId(final long userId) {
//		return this.roleRepository.findRolesByUserId(userId);
//	}
    
    /**
     * 
     * @param role
     */
    public void validate(final Role role) {
        Map<String, String> details = new HashMap<>();
        
        if (null == role.getName() || role.getName().isEmpty()) {
            details.put("name", "required");
        }
        
        if (null == role.getFunctions() || role.getFunctions().isEmpty()) {
        	details.put("functions", "no function selected");
        }
    }

	@Override
	public void delete(final Long roleId) {
		this.roleRepository.deleteById(roleId);
	}

}
