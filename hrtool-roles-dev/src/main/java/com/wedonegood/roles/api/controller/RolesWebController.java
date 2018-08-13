package com.wedonegood.roles.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wedonegood.common.security.UserInfoContext;
import com.wedonegood.roles.api.FunctionService;
import com.wedonegood.roles.api.RoleService;
import com.wedonegood.roles.api.model.entity.Function;
import com.wedonegood.roles.rest.v1.dto.FunctionDto;
import com.wedonegood.roles.rest.v1.dto.RoleDto;

@Controller
@RequestMapping(value = "/{locale:en|es|fr}/roles")
public class RolesWebController {
	
	private final static String VIEW_NAME = "roles";
	private final static String VIEW_NAME_EDIT = "editRoles";
	
	@Autowired
	private FunctionService functionService;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("")
    public String index(final Model model) {
		model.addAttribute("roleList", this.roleService.getRoles(UserInfoContext.getCurrent().getClientId(), PageRequest.of(0, 10, Sort.Direction.ASC, "id")));
		
        return VIEW_NAME;
    }
	
	@GetMapping("/new")
    public String newRole(final Model model) {
		final List<FunctionDto> fdtoList = new ArrayList<FunctionDto>();
		final List<Function> fList = this.functionService.getFunctions();
		
		for (final Function f : fList) {
			fdtoList.add(new FunctionDto(f));
		}
		
		model.addAttribute("functionList", fdtoList);
		model.addAttribute("role", new RoleDto());
		
		System.out.println("New Role");
		
        return VIEW_NAME_EDIT;
    }
	
	@GetMapping("/edit/{roleId}")
    public String editRole(@PathVariable("roleId") final Long roleId, final Model model) {
		final List<FunctionDto> fdtoList = new ArrayList<FunctionDto>();
		final List<Function> fList = this.functionService.getFunctions();
		
		for (final Function f : fList) {
			fdtoList.add(new FunctionDto(f));
		}
		
		model.addAttribute("functionList", fdtoList);
		model.addAttribute("role", new RoleDto(this.roleService.get(roleId)));
		System.out.println(new RoleDto(this.roleService.get(roleId)).getFunctions());
		System.out.println(fdtoList);
		
		System.out.println("Edit Role");
		
        return VIEW_NAME_EDIT;
    }
}
