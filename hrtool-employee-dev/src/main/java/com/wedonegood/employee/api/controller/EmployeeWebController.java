package com.wedonegood.employee.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wedonegood.biling.BilingService;
import com.wedonegood.common.client.Client;
import com.wedonegood.common.language.LanguageService;
import com.wedonegood.common.security.UserInfoContext;
import com.wedonegood.contract.ContractService;
import com.wedonegood.employee.api.EmployeeService;
import com.wedonegood.employee.rest.v1.dto.EmployeeDto;
import com.wedonegood.groups.api.GroupService;
import com.wedonegood.permit.PermitService;
import com.wedonegood.roles.api.RoleService;

@Controller
@RequestMapping(value = "/{locale:en|es|fr}/employees")
public class EmployeeWebController {
	
	private final static String VIEW_NAME = "employees";
	private final static String VIEW_NAME_EDIT = "editEmployee";
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private PermitService permitService;
	
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private BilingService bilingService;
	
	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("")
    public String index(final Model model) {
		
		model.addAttribute("employeeList", this.employeeService.getEmployees(UserInfoContext.getCurrent().getClientId(), PageRequest.of(0, 10, Sort.Direction.ASC, "id")));
		
        return VIEW_NAME;
    }
	
	@GetMapping("/new")
    public String newEmployee(final Model model) {
		model.addAttribute("employee", new EmployeeDto());
		model.addAttribute("employee", new EmployeeDto());
		
		Client client = new Client();
		client.setId(1L);
		model.addAttribute("groupList", this.groupService.listAllByClient(client));
		model.addAttribute("permitList", this.permitService.getPermits());
		model.addAttribute("contractList", this.contractService.getContracts());
		model.addAttribute("bilingList", this.bilingService.getBilings());
		model.addAttribute("languageList", this.languageService.getLanguages());
		model.addAttribute("roleList", this.roleService.findAllByClientAndActiveIsTrue(client));
		
		System.out.println("New Employee");
		
        return VIEW_NAME_EDIT;
    }
	
	@GetMapping("/edit/{employeeId}")
    public String editEmployee(@PathVariable("employeeId") final Long employeeId, final Model model) {
		model.addAttribute("employee", new EmployeeDto(this.employeeService.get(employeeId)));
		
		Client client = new Client();
		client.setId(1L);
		model.addAttribute("groupList", this.groupService.listAllByClient(client));
		model.addAttribute("permitList", this.permitService.getPermits());
		model.addAttribute("contractList", this.contractService.getContracts());
		model.addAttribute("bilingList", this.bilingService.getBilings());
		model.addAttribute("languageList", this.languageService.getLanguages());
		model.addAttribute("roleList", this.roleService.findAllByClientAndActiveIsTrue(client));
		
		System.out.println("Edit Employee");
		
        return VIEW_NAME_EDIT;
    }
}
