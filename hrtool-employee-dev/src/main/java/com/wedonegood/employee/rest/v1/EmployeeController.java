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

import com.wedonegood.employee.api.EmployeeService;
import com.wedonegood.employee.api.model.entity.Employee;
import com.wedonegood.employee.rest.common.PagingController;
import com.wedonegood.employee.rest.v1.dto.EmployeeDto;
import com.wedonegood.groups.api.GroupService;

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
	
	@GetMapping("/list/{page}")
    @ApiOperation(value = "Get employees", nickname = "getEmployees")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of employees", response = EmployeeDto.class, responseContainer = "List")
    })
    public ResponseEntity<List<EmployeeDto>> getEmployees(@PathVariable("page") final Optional<Integer> page) {
        final Pageable pageable = PageRequest.of(page.orElse(0), 10, Sort.Direction.ASC, "id");
        final Page<Employee> g = this.employeeService.getEmployees(pageable);
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
		
//        employee.setName(employeeDto.getName());
        
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
        employee.getUser().setUserEmail(employeeDto.getUser().getUserEmail());
        employee.getUser().setUserPhone(employeeDto.getUser().getUserPhone());
        
        // Employee
        employee.setNumber(employeeDto.getNumber());
        employee.setNin(employeeDto.getNin());
        employee.setCountry(employeeDto.getCountry());
        employee.setCity(employeeDto.getCity());
        employee.setPostalCode(employeeDto.getPostalCode());
        employee.setBirthday(employeeDto.getBirthday());
        employee.setPassportScan(employeeDto.getPassportScan());
        
//        employee.setPermit(this.permitService.get(employeeDto.getPermitId()));
//        employee.setContract(this.contractService.get(employeeDto.getContractId()));
        employee.setJobPosition(employeeDto.getJobPosition());
//        employee.setBiling(this.bilingService.get(employeeDto.getBilingId()));
        employee.setHireDate(employeeDto.getHireDate());
        employee.setSalary(employeeDto.getSalary());
        employee.setGroup(this.groupService.get(employeeDto.getGroup().getGroupId()));
        employee.setVacationAllowance(employeeDto.getVacationAllowance());
        
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
        
        employee.setActive(false);
        
        this.employeeService.save(employee);
        
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
