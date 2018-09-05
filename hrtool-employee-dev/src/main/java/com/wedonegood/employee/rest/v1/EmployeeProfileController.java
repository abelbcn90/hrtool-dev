package com.wedonegood.employee.rest.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wedonegood.common.model.user.UserService;
import com.wedonegood.employee.api.EmployeeService;
import com.wedonegood.employee.api.model.entity.Employee;
import com.wedonegood.employee.rest.common.PagingController;
import com.wedonegood.employee.rest.v1.dto.EmployeeDto;

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
@RequestMapping(value = "/api/v1/profile")
@Api(value="Profile", description="Operations pertaining to Profile", position = 3)
public class EmployeeProfileController extends PagingController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private CityService cityService;
	
	@PatchMapping("/")
    @ApiOperation(value = "Update employee's profile", nickname = "updateProfile")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Employee Updated", response = EmployeeDto.class),
            @ApiResponse(code = 208, message = "This email address is being used by another user. Please pick a different one or contact your administrator.", response = EmployeeDto.class)
    })
	public ResponseEntity<EmployeeDto> updateProfile(@RequestBody final EmployeeDto employeeDto) {
		Employee employee = this.employeeService.get(employeeDto.getEmployeeId());
        
        if (null == employee) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        if (!employee.isActive()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        
        employee.getUser().setUserPhone(employeeDto.getUser().getUserPhone());
		
        if (null != employeeDto.getUser().getPassword() && !employeeDto.getUser().getPassword().isEmpty()) {
        	this.userService.changePassword(employee.getUser().getId(), employeeDto.getUser().getPassword());
        	employee.getUser().setUserChangePwd(false);
        }
        
        employee.setAddress(employeeDto.getAddress());
        employee.setCountry(this.countryService.findCountryByCode(employeeDto.getCountry().getCountryCode()));
        employee.setCity(this.cityService.findCityById(employeeDto.getCity().getCityId()));
        employee.setPostalCode(employeeDto.getPostalCode());
        employee.setBirthday(employeeDto.getBirthday());
        
        employee = this.employeeService.save(employee);
        
        return ResponseEntity.ok(new EmployeeDto(employee));
    }
	
	@GetMapping(value = "/{userId}", produces = "application/json")
    @ApiOperation(value = "Get employee's profile data", nickname = "getEmployeeProfileByUserId")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get Employee's profile", response = EmployeeDto.class)
    })
    public ResponseEntity<EmployeeDto> getEmployeeProfile(@PathVariable("userId") final Long userId) {
		final Employee employee = this.employeeService.getEmployee(userId);

        if (null == employee) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!employee.isActive()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return ResponseEntity.ok(new EmployeeDto(employee));
    }
}
