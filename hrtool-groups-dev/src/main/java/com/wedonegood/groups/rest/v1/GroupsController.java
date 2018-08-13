package com.wedonegood.groups.rest.v1;

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

import com.wedonegood.calendar.api.model.entity.Calendar;
import com.wedonegood.common.client.ClientService;
import com.wedonegood.common.security.UserInfoContext;
import com.wedonegood.groups.api.GroupService;
import com.wedonegood.groups.api.model.entity.Groups;
import com.wedonegood.groups.calendar.api.CalendarService;
import com.wedonegood.groups.rest.common.PagingController;
import com.wedonegood.groups.rest.v1.dto.GroupDto;
import com.wedonegood.groups.working.hours.api.WorkingHoursService;
import com.wedonegood.working.hours.api.model.entity.WorkingHours;

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
@RequestMapping(value = "/api/v1/group")
@Api(value="Group", description="Operations pertaining to Group", position = 3)
public class GroupsController extends PagingController {
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private WorkingHoursService workingHoursService;
	
	@Autowired
	private CalendarService calendarService;
	
	@Autowired
    private ClientService clientService;
	
	@GetMapping("/list/{page}")
    @ApiOperation(value = "Get groups", nickname = "getGroups")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of groups", response = GroupDto.class, responseContainer = "List")
    })
    public ResponseEntity<List<GroupDto>> getGroups(@PathVariable("page") final Optional<Integer> page) {
        final Pageable pageable = PageRequest.of(page.orElse(0), 10, Sort.Direction.ASC, "id");
        final Page<Groups> g = this.groupService.getGroups(UserInfoContext.getCurrent().getClientId(), pageable);
        
        for (final Groups groups : g) {
        	groups.setEmployeesNumber(this.groupService.findNumberOfEmployeesByGroup(groups.getId()));
        }
        
        final ResponseEntity<List<GroupDto>> resp = this.pageResponse(g, GroupDto::new);
        
        return resp;
    }
	
	@PutMapping("/")
    @ApiOperation(value = "Add new group", nickname = "addGroup")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "new group", response = GroupDto.class)
    })
    public ResponseEntity<GroupDto> addGroup(@RequestBody final GroupDto groupDto) {
		Groups group = new Groups();
		
		final WorkingHours workingHours = this.workingHoursService.getWorkingHours(groupDto.getWorkingHours().getId());
        final Calendar calendar = this.calendarService.get(groupDto.getCalendar().getId());
        
        group.setName(groupDto.getName());
        group.setWorkingHours(workingHours);
        group.setCalendar(calendar);
        group.setClient(this.clientService.get(UserInfoContext.getCurrent().getClientId()));
        
        group = this.groupService.save(group);
        
        return ResponseEntity.ok(new GroupDto(group));
    }
	
	@PatchMapping("/")
    @ApiOperation(value = "Update group", nickname = "updateGroup")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Updated group", response = GroupDto.class)
    })
    public ResponseEntity<GroupDto> updateGroup(@RequestBody final GroupDto groupDto) {
        Groups group = this.groupService.get(groupDto.getGroupId());
        final WorkingHours workingHours = this.workingHoursService.getWorkingHours(groupDto.getWorkingHours().getId());
        final Calendar calendar = this.calendarService.get(groupDto.getCalendar().getId());
        
        if (null == group) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        if (!group.isActive()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        
        group.setName(groupDto.getName());
        group.setWorkingHours(workingHours);
        group.setCalendar(calendar);
        group = this.groupService.save(group);
        
        return ResponseEntity.ok(new GroupDto(group));
    }
	
    @DeleteMapping("/{groupId}")
    @ApiOperation(value = "Delete Group", nickname = "deleteGroup")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Group Deleted")
    })
    public ResponseEntity<Object> deleteGroup(@PathVariable("groupId") final Long groupId) {
        final Groups group = this.groupService.get(groupId);
        
        if (null == group) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        if (!group.isActive()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        
        group.setActive(false);
        
        this.groupService.save(group);
        
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{groupId}", produces = "application/json")
    @ApiOperation(value = "Get full Group data", nickname = "getGroupById")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get Group", response = GroupDto.class)
    })
    public ResponseEntity<GroupDto> getGroup(@PathVariable("groupId") final Long groupId) {
        final Groups group = this.groupService.get(groupId);
        
        if (null == group) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        if (!group.isActive()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        
        return ResponseEntity.ok(new GroupDto(group));
    }
}
