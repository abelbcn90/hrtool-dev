package com.wedonegood.uploads;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wedonegood.employee.api.EmployeeService;
import com.wedonegood.employee.api.model.entity.Employee;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Transactional
@RestController
@RequestMapping(value = "/api/v1/uploads")
@Api(value="Uploads", description="Operations pertaining to uploads", position = 6)
public class UploadController {
	
	private final static String IMAGES_SERVICE_PATH = "/api/v1/img/";

    @Autowired
    private UploadService uploadService;
    
    @Autowired
    private EmployeeService employeeService;

    @PutMapping(path = "/profilePicture/{employeeId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
    @ApiOperation(value = "Create new profilePicture", code = 201)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Access denied")}
    )
    public ResponseEntity<String> createProfilePicture(@PathVariable("employeeId") final Long employeeId, @RequestPart(required = true) MultipartFile file) throws IOException {
        final String path = this.uploadService.createProfilePicture(employeeId, file.getOriginalFilename(), file.getInputStream());
        
        final Employee employee = this.employeeService.get(employeeId);
    	employee.setProfilePicture(path);
    	this.employeeService.save(employee);
        
        return ResponseEntity.ok(IMAGES_SERVICE_PATH + "profilePicture/" + employee.getId() + "/full");
    }
    
    @PutMapping(path = "/passportScan/{employeeId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
    @ApiOperation(value = "Create new passportScan", code = 201)
    @ApiResponses(value = {
    		@ApiResponse(code = 201, message = "Created"),
    		@ApiResponse(code = 400, message = "Bad request"),
    		@ApiResponse(code = 401, message = "Access denied")}
    		)
    public ResponseEntity<String> createPassportScan(@PathVariable("employeeId") final Long employeeId, @RequestPart(required = true) MultipartFile file) throws IOException {
    	final String path = this.uploadService.createPassportScan(employeeId, file.getOriginalFilename(), file.getInputStream());
    	
    	final Employee employee = this.employeeService.get(employeeId);
    	employee.setPassportScan(path);
    	this.employeeService.save(employee);
    	
    	return ResponseEntity.ok(IMAGES_SERVICE_PATH + "passportScan/" + employee.getId() + "/full");
    }
}
