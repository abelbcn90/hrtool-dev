package com.wedonegood.roles.rest.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wedonegood.roles.api.FunctionService;
import com.wedonegood.roles.api.RoleService;
import com.wedonegood.roles.api.model.entity.Function;
import com.wedonegood.roles.api.model.entity.Role;
import com.wedonegood.roles.rest.common.PagingController;
import com.wedonegood.roles.rest.v1.dto.FunctionDto;
import com.wedonegood.roles.rest.v1.dto.RoleDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author Abel Pulido Ponce
 *
 */
@Transactional
@RestController
@RequestMapping(value = "/api/v1/role")
@Api(value="Role", description="Operations pertaining to Role", position = 3)
public class RoleController extends PagingController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private FunctionService functionService;
	
	@GetMapping("/list/{page}")
    @ApiOperation(value = "Get roles", nickname = "getRoles")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of roles", response = RoleDto.class, responseContainer = "List")
    })
    public ResponseEntity<List<RoleDto>> getRoles(@PathVariable("page") final Optional<Integer> page) {
        final Pageable pageable = PageRequest.of(page.orElse(0), 10, Sort.Direction.ASC, "id");
        final Page<Role> g = this.roleService.getRoles(pageable);
        final ResponseEntity<List<RoleDto>> resp = this.pageResponse(g, RoleDto::new);
        
        return resp;
    }
	
	@PutMapping("/")
    @ApiOperation(value = "Add new role", nickname = "addRole")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "new group", response = RoleDto.class)
    })
    public ResponseEntity<RoleDto> addRole(@RequestBody final RoleDto roleDto) {
		final List<Function> functions = new ArrayList<Function>();
		for (final FunctionDto fdto : roleDto.getFunctions()) {
			functions.add(this.functionService.get(fdto.getFunctionId()));
		}
		
        final Role role = this.roleService.save(new Role(roleDto.getName(), functions));
        
        return ResponseEntity.ok(new RoleDto(role));
    }
	
	@PatchMapping("/")
    @ApiOperation(value = "Update role", nickname = "updateRole")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Updated role", response = RoleDto.class)
    })
    public ResponseEntity<RoleDto> updateRole(@RequestBody final RoleDto roleDto) {
        Role role = this.roleService.get(roleDto.getRoleId());
        
        if (null == role) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        if(!role.isActive()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        
        final List<Function> functions = role.getFunctions();
        role.getFunctions().clear();
        
		for (final FunctionDto fdto : roleDto.getFunctions()) {
			final Function functionTemp = this.functionService.get(fdto.getFunctionId());
    		
			functions.add(functionTemp);
		}
        
        role.setName(roleDto.getName());
        role.setFunctions(functions);
        
        role = this.roleService.save(role);
        
        return ResponseEntity.ok(new RoleDto(role));
    }
	
    @DeleteMapping("/{roleId}")
    @ApiOperation(value = "Delete role", nickname = "deleteRole")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deleted")
    })
    public ResponseEntity<Object> deleteRole(@PathVariable("roleId") final Long roleId) {
    	final Role role = this.roleService.get(roleId);
        if (null == role) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        if (!role.isActive()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        
        role.setActive(false);
        
        this.roleService.save(role);
        
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{roleId}", produces = "application/json")
    @ApiOperation(value = "Get full Role data", nickname = "getRoleById")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Role", response = RoleDto.class)
    })
    public ResponseEntity<RoleDto> getRole(@PathVariable("roleId") final Long roleId) {
    	final Role role = this.roleService.get(roleId);
        
        if (null == role) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        if (!role.isActive()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        
        return ResponseEntity.ok(new RoleDto(role));
    }
}
