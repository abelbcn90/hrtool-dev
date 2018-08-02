package com.wedonegood.employee.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wedonegood.employee.api.EmployeeService;
import com.wedonegood.employee.rest.v1.dto.EmployeeDto;
import com.wedonegood.groups.api.GroupService;
import com.wedonegood.groups.client.entity.Client;

@Controller
@RequestMapping(value = "/{locale:en|es|fr}/employees")
public class EmployeeWebController {
	
	private final static String VIEW_NAME = "employees";
	private final static String VIEW_NAME_EDIT = "editEmployee";
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private GroupService groupService;
	
	@GetMapping("")
    public String index(final Model model) {
		
		model.addAttribute("employeeList", this.employeeService.getEmployees(PageRequest.of(0, 10, Sort.Direction.ASC, "id")));
		
        return VIEW_NAME;
    }
	
	@GetMapping("/new")
    public String newEmployee(final Model model) {
		model.addAttribute("employee", new EmployeeDto());
		model.addAttribute("employee", new EmployeeDto());
		
//		model.addAttribute("workingHoursList", this.workingHoursService.getWorkingHours());
//		model.addAttribute("calendarList", this.calendarService.getCalendars(PageRequest.of(0, 10, Sort.Direction.ASC, "id")));
		
		System.out.println("New Employee");
		
        return VIEW_NAME_EDIT;
    }
	
	@GetMapping("/edit/{employeeId}")
    public String editEmployee(@PathVariable("employeeId") final Long employeeId, final Model model) {
		model.addAttribute("employee", new EmployeeDto(this.employeeService.get(employeeId)));
		Client client = new Client();
		client.setId(1L);
		model.addAttribute("groupList", this.groupService.listAllByClient(client));
//		model.addAttribute("workingHoursList", this.workingHoursService.getWorkingHours());
//		model.addAttribute("calendarList", this.calendarService.getCalendars(PageRequest.of(0, 10, Sort.Direction.ASC, "id")));
		
		System.out.println("Edit Employee");
		
        return VIEW_NAME_EDIT;
    }
}
