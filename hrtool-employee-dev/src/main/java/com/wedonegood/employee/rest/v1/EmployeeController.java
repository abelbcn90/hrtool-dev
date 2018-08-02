package com.wedonegood.employee.rest.v1;

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

import com.wedonegood.biling.BilingService;
import com.wedonegood.common.client.ClientService;
import com.wedonegood.common.language.LanguageService;
import com.wedonegood.common.model.user.UserService;
import com.wedonegood.common.security.UserInfoContext;
import com.wedonegood.common.user.api.model.entity.User;
import com.wedonegood.contract.ContractService;
import com.wedonegood.employee.api.EmployeeService;
import com.wedonegood.employee.api.model.entity.Employee;
import com.wedonegood.employee.rest.common.PagingController;
import com.wedonegood.employee.rest.v1.dto.EmployeeDto;
import com.wedonegood.groups.api.GroupService;
import com.wedonegood.permit.PermitService;

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
@RequestMapping(value = "/api/v1/employee")
@Api(value="Employee", description="Operations pertaining to Employees", position = 3)
public class EmployeeController extends PagingController {
	
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
	private UserService userService;
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping("/list/{page}")
    @ApiOperation(value = "Get employees", nickname = "getEmployees")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of employees", response = EmployeeDto.class, responseContainer = "List")
    })
    public ResponseEntity<List<EmployeeDto>> getEmployees(@PathVariable("page") final Optional<Integer> page) {
        final Pageable pageable = PageRequest.of(page.orElse(0), 10, Sort.Direction.ASC, "id");
        final Page<Employee> g = this.employeeService.getEmployees(UserInfoContext.getCurrent().getClientId(), pageable);
        final ResponseEntity<List<EmployeeDto>> resp = this.pageResponse(g, EmployeeDto::new);
        
        return resp;
    }
	
	@PutMapping("/")
    @ApiOperation(value = "Add new employee", nickname = "addEmployee")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "new employee", response = EmployeeDto.class)
    })
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody final EmployeeDto employeeDto) {
		Employee employee = new Employee();
		User user = new User();
		
		// User
		user.setUserFirstName(employeeDto.getUser().getUserFirstName());
		user.setUserLastName(employeeDto.getUser().getUserLastName());
		
		if (null != this.userService.getUserByUserEmail(employeeDto.getUser().getUserEmail())) {
        	return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }
		
		user.setUserEmail(employeeDto.getUser().getUserEmail());
		user.setUserPhone(employeeDto.getUser().getUserPhone());
		user.setLanguage(this.languageService.findLanguageByCode(employeeDto.getUser().getLanguageCode()));
		user.setUserChangePwd(false);
		user.setClient(this.clientService.get(UserInfoContext.getCurrent().getClientId()));
		
		user = this.userService.save(user);
		this.userService.changePassword(user.getId(), employeeDto.getUser().getPassword());
		employee.setUser(user);
        
        // Employee
        employee.setNumber(employeeDto.getNumber());
        employee.setNin(employeeDto.getNin());
        employee.setAddress(employeeDto.getAddress());
        employee.setCountry(employeeDto.getCountry());
        employee.setCity(employeeDto.getCity());
        employee.setPostalCode(employeeDto.getPostalCode());
        employee.setBirthday(employeeDto.getBirthday());
        employee.setPassportScan(employeeDto.getPassportScan());
        
        // Job details
        employee.setPermit(this.permitService.get(employeeDto.getPermitId()));
        employee.setContract(this.contractService.get(employeeDto.getContractId()));
        employee.setJobPosition(employeeDto.getJobPosition());
        employee.setBiling(this.bilingService.get(employeeDto.getBilingId()));
        employee.setHireDate(employeeDto.getHireDate());
        employee.setSalary(employeeDto.getSalary());
        employee.setGroup(this.groupService.get(employeeDto.getGroup().getGroupId()));
        employee.setVacationAllowance(employeeDto.getVacationAllowance());
        
        // User Role
		
        employee = this.employeeService.save(employee);
        
        return ResponseEntity.ok(new EmployeeDto(employee));
    }
	
	@PatchMapping("/")
    @ApiOperation(value = "Update employee", nickname = "updateEmployee")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Employee Updated", response = EmployeeDto.class)
    })
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody final EmployeeDto employeeDto) {
		Employee employee = this.employeeService.get(employeeDto.getEmployeeId());
        
        if (null == employee) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        if (!employee.isActive()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        
        // User
        employee.getUser().setUserFirstName(employeeDto.getUser().getUserFirstName());
        employee.getUser().setUserLastName(employeeDto.getUser().getUserLastName());
        
        final User userTemp = this.userService.getUserByUserEmail(employeeDto.getUser().getUserEmail());
        if (null != userTemp && employee.getUser().getId() != userTemp.getId()) {
        	return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        } else {
        	employee.getUser().setUserEmail(employeeDto.getUser().getUserEmail());
        }
        
        employee.getUser().setUserPhone(employeeDto.getUser().getUserPhone());
        employee.getUser().setLanguage(this.languageService.findLanguageByCode(employeeDto.getUser().getLanguageCode()));
		
        if (null != employeeDto.getUser().getPassword() && !employeeDto.getUser().getPassword().isEmpty()) {
        	this.userService.changePassword(employee.getUser().getId(), employeeDto.getUser().getPassword());
        }
        
        employee.getUser().setUserChangePwd(false);
        
        // Employee
        employee.setNumber(employeeDto.getNumber());
        employee.setNin(employeeDto.getNin());
        employee.setAddress(employeeDto.getAddress());
        employee.setCountry(employeeDto.getCountry());
        employee.setCity(employeeDto.getCity());
        employee.setPostalCode(employeeDto.getPostalCode());
        employee.setBirthday(employeeDto.getBirthday());
        employee.setPassportScan(employeeDto.getPassportScan());
        
        // Job details
        employee.setPermit(this.permitService.get(employeeDto.getPermitId()));
        employee.setContract(this.contractService.get(employeeDto.getContractId()));
        employee.setJobPosition(employeeDto.getJobPosition());
        employee.setBiling(this.bilingService.get(employeeDto.getBilingId()));
        employee.setHireDate(employeeDto.getHireDate());
        employee.setSalary(employeeDto.getSalary());
        employee.setGroup(this.groupService.get(employeeDto.getGroup().getGroupId()));
        employee.setVacationAllowance(employeeDto.getVacationAllowance());
        
        // User Role
        
        employee = this.employeeService.save(employee);
        
        return ResponseEntity.ok(new EmployeeDto(employee));
    }
	
    @DeleteMapping("/{employeeId}")
    @ApiOperation(value = "Delete Employee", nickname = "deleteEmployee")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Employee Deleted")
    })
    public ResponseEntity<Object> deleteEmployee(@PathVariable("employeeId") final Long employeeId) {
        final Employee employee = this.employeeService.get(employeeId);
        
        if (null == employee) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        if (!employee.isActive()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        
        final User user = this.userService.getUserByUserId(employee.getUser().getId());
        
        employee.setActive(false);
        user.setActive(false);
        
        this.employeeService.save(employee);
        this.userService.save(user);
        
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{employeeId}", produces = "application/json")
    @ApiOperation(value = "Get full Employee data", nickname = "getEmployeeById")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get Employee", response = EmployeeDto.class)
    })
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable("employeeId") final Long employeeId) {
        final Employee employee = this.employeeService.get(employeeId);
        
        if (null == employee) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        if (!employee.isActive()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        
        return ResponseEntity.ok(new EmployeeDto(employee));
    }
}
