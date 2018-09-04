package com.wedonegood.employee.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wedonegood.billing.BillingService;
import com.wedonegood.common.language.LanguageService;
import com.wedonegood.common.model.user.UserService;
import com.wedonegood.common.security.UserInfoContext;
import com.wedonegood.contract.ContractService;
import com.wedonegood.employee.api.EmployeeService;
import com.wedonegood.employee.api.model.entity.Employee;
import com.wedonegood.employee.rest.v1.CityService;
import com.wedonegood.employee.rest.v1.dto.EmployeeDto;
import com.wedonegood.groups.api.GroupService;
import com.wedonegood.permit.PermitService;
import com.wedonegood.roles.api.RoleService;
import com.wedonegood.userRole.UserRoleService;

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
	private BillingService billingService;
	
	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CityService cityService;
	
	@GetMapping("")
    public String index(final Model model) {
		model.addAttribute("employeeList", this.employeeService.getEmployees(UserInfoContext.getCurrent().getClientId(), PageRequest.of(0, 10, Sort.Direction.ASC, "id")));
//		model.addAttribute("employeeList", this.employeeService.searchEmployees(UserInfoContext.getCurrent().getClientId(), "dav", PageRequest.of(0, 10, Sort.Direction.ASC, "id")));
		
        return VIEW_NAME;
    }
	
	@GetMapping("/new")
    public String newEmployee(final Model model) {
		model.addAttribute("employee", new EmployeeDto());
		
		model.addAttribute("groupList", this.groupService.listAllByClientId(UserInfoContext.getCurrent().getClientId()));
		model.addAttribute("permitList", this.permitService.getPermits());
		model.addAttribute("contractList", this.contractService.getContracts());
		model.addAttribute("billingList", this.billingService.getBilings());
		model.addAttribute("languageList", this.languageService.getLanguages());
		model.addAttribute("roleList", this.roleService.findAllByClientIdAndActiveIsTrue(UserInfoContext.getCurrent().getClientId()));
		model.addAttribute("managerList", this.userService.findManagers(UserInfoContext.getCurrent().getClientId()));
		model.addAttribute("cityList", this.cityService.getCities());
		
		System.out.println("New Employee");
		
        return VIEW_NAME_EDIT;
    }
	
	@GetMapping("/edit/{employeeId}")
    public String editEmployee(@PathVariable("employeeId") final Long employeeId, final Model model) {
		final Employee employee = this.employeeService.get(employeeId);
		employee.setRoles(this.roleService.findRolesFromUserRoleByUserIdAndActiveIsTrue(employee.getUser().getId()));
		
		model.addAttribute("employee", new EmployeeDto(employee));
		
		model.addAttribute("groupList", this.groupService.listAllByClientId(UserInfoContext.getCurrent().getClientId()));
		model.addAttribute("permitList", this.permitService.getPermits());
		model.addAttribute("contractList", this.contractService.getContracts());
		model.addAttribute("billingList", this.billingService.getBilings());
		model.addAttribute("languageList", this.languageService.getLanguages());
		model.addAttribute("roleList", this.roleService.findAllByClientIdAndActiveIsTrue(UserInfoContext.getCurrent().getClientId()));
		model.addAttribute("userRoleList", this.userRoleService.findAllByUserIdAndActiveIsTrue(employee.getUser().getId()));
		model.addAttribute("managerList", this.userService.findManagers(UserInfoContext.getCurrent().getClientId()));
		model.addAttribute("cityList", this.cityService.getCities());
		
		System.out.println("Edit Employee");
		
        return VIEW_NAME_EDIT;
    }
}
