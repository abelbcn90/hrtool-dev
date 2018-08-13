package com.wedonegood.groups.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wedonegood.common.security.UserInfoContext;
import com.wedonegood.groups.api.GroupService;
import com.wedonegood.groups.calendar.api.CalendarService;
import com.wedonegood.groups.rest.v1.dto.GroupDto;
import com.wedonegood.groups.working.hours.api.WorkingHoursService;

@Controller
@RequestMapping(value = "/{locale:en|es|fr}/groups")
public class GroupsWebController {
	
	private final static String VIEW_NAME = "groups";
	private final static String VIEW_NAME_EDIT = "editGroup";
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private WorkingHoursService workingHoursService;
	
	@Autowired
	private CalendarService calendarService;
	
	@GetMapping("")
    public String index(final Model model) {
		model.addAttribute("groupList", this.groupService.getGroups(UserInfoContext.getCurrent().getClientId(), PageRequest.of(0, 10, Sort.Direction.ASC, "id")));
		
        return VIEW_NAME;
    }
	
	@GetMapping("/new")
    public String newGroup(final Model model) {
		model.addAttribute("group", new GroupDto());
		model.addAttribute("workingHoursList", this.workingHoursService.getWorkingHours());
		model.addAttribute("calendarList", this.calendarService.getCalendars(PageRequest.of(0, 10, Sort.Direction.ASC, "id")));
		
		System.out.println("New Group");
		
        return VIEW_NAME_EDIT;
    }
	
	@GetMapping("/edit/{groupId}")
    public String editGroup(@PathVariable("groupId") final Long groupId, final Model model) {
		model.addAttribute("group", new GroupDto(this.groupService.get(groupId)));
		model.addAttribute("workingHoursList", this.workingHoursService.getWorkingHours());
		model.addAttribute("calendarList", this.calendarService.getCalendars(PageRequest.of(0, 10, Sort.Direction.ASC, "id")));
		
		System.out.println("Edit Group");
		
        return VIEW_NAME_EDIT;
    }
}
