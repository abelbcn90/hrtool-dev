package com.wedonegood.uploads;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wedonegood.employee.api.EmployeeService;
import com.wedonegood.employee.api.model.entity.Employee;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Transactional
@RestController
@RequestMapping(value = "/api/v1/img")
@Api(value="Image", description="Operations pertaining to images", position = 6)
public class ImageController {

	final static int SCALED_SIZE_BIG = 138;
	final static int SCALED_SIZE_SMALL = 44;
	final static int SCALED_SIZE_PASSPORT = 99;
	
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private UploadService uploadService;

    @GetMapping(value = "/profilePicture/{employeeId}/small", produces = {"image/png", "image/jpg"})
    @ApiOperation(value = "Get small profile picture", code = 200)
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Get small profile picture")
    })
    public ResponseEntity getSmallProfilePicture(@PathVariable("employeeId") final Long employeeId) throws IOException {
        final Employee employee = this.employeeService.get(employeeId);
    	
        if (null == employee || null == employee.getProfilePicture() || employee.getProfilePicture().isEmpty()) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
        
        final File file = new File(this.uploadService.getFilePath(employee.getId(), "small" + employee.getProfilePicture().substring(employee.getProfilePicture().lastIndexOf("."))));
        final String contentType = Files.probeContentType(file.toPath());
        final InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        
        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
    
    @GetMapping(value = "/profilePicture/{employeeId}/big", produces = {"image/png", "image/jpg"})
    @ApiOperation(value = "Get big profile picture", code = 200)
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Get big profile picture")
    })
    public ResponseEntity getBigProfilePicture(@PathVariable("employeeId") final Long employeeId) throws IOException {
final Employee employee = this.employeeService.get(employeeId);
    	
        if (null == employee || null == employee.getProfilePicture() || employee.getProfilePicture().isEmpty()) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
        
        final File file = new File(this.uploadService.getFilePath(employee.getId(), "big" + employee.getProfilePicture().substring(employee.getProfilePicture().lastIndexOf("."))));
        final String contentType = Files.probeContentType(file.toPath());
        final InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        
        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
    
    @GetMapping(value = "/profilePicture/{employeeId}/full", produces = {"image/png", "image/jpg"})
    @ApiOperation(value = "Get big profile picture", code = 200)
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Get full profile picture")
    })
    public ResponseEntity getProfilePictureFull(@PathVariable("employeeId") final Long employeeId) throws IOException {
    	final Employee employee = this.employeeService.get(employeeId);
    	
    	if (null == employee || null == employee.getProfilePicture() || employee.getProfilePicture().isEmpty()) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    	
    	final File file = new File(employee.getProfilePicture());
    	final String contentType = Files.probeContentType(file.toPath());
    	final InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
    	
    	return ResponseEntity.ok()
    			.contentLength(file.length())
    			.contentType(MediaType.parseMediaType(contentType))
    			.body(resource);
    }
    
    @GetMapping(value = "/passportScan/{employeeId}/preview", produces = {"image/png", "image/jpg", "application/pdf"})
    @ApiOperation(value = "Get passport scan preview", code = 200)
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Get passport scan preview")
    })
    public ResponseEntity getPassportScanPreview(@PathVariable("employeeId") final Long employeeId) throws IOException {
    	final Employee employee = this.employeeService.get(employeeId);
    	
        if (null == employee || null == employee.getPassportScan() || employee.getPassportScan().isEmpty()) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
        
        final File file = new File(this.uploadService.getFilePath(employee.getId(), "preview.jpg"));
        final String contentType = Files.probeContentType(file.toPath());
        final InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        
        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
    
    @GetMapping(value = "/passportScan/{employeeId}/full", produces = {"image/png", "image/jpg", "application/pdf"})
    @ApiOperation(value = "Get passport scan", code = 200)
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Get passport scan full")
    })
    public ResponseEntity getPassportScanFull(@PathVariable("employeeId") final Long employeeId) throws IOException {
    	final Employee employee = this.employeeService.get(employeeId);
    	
    	if (null == employee || null == employee.getPassportScan() || employee.getPassportScan().isEmpty()) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    	
    	final File file = new File(employee.getPassportScan());
    	final String contentType = Files.probeContentType(file.toPath());
    	final InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
    	
    	return ResponseEntity.ok()
    			.contentLength(file.length())
    			.contentType(MediaType.parseMediaType(contentType))
    			.body(resource);
    }
}
