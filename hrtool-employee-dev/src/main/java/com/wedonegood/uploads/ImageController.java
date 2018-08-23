package com.wedonegood.uploads;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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

	private final static String FILE_NAME_SMALL = "file.name.small";
    private final static String FILE_NAME_MEDIUM = "file.name.medium";
    private final static String FILE_NAME_BIG = "file.name.big";
    private final static String FILE_NAME_PREVIEW = "file.name.preview";
	
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private UploadService uploadService;
    
    @Autowired
	private Environment env;

    @GetMapping(value = "/profilePicture/{employeeId}/small", produces = {"image/png", "image/jpg"})
    @ApiOperation(value = "Get small profile picture", code = 200)
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Get small profile picture")
    })
    public ResponseEntity getSmallProfilePicture(@PathVariable("employeeId") final Long employeeId) throws IOException {
    	return this.getProfilePictureBySize(employeeId, this.env.getProperty(FILE_NAME_SMALL));
    }
    
    @GetMapping(value = "/profilePicture/{employeeId}/medium", produces = {"image/png", "image/jpg"})
    @ApiOperation(value = "Get medium profile picture", code = 200)
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Get medium profile picture")
    })
    public ResponseEntity getMediumProfilePicture(@PathVariable("employeeId") final Long employeeId) throws IOException {
    	return this.getProfilePictureBySize(employeeId, this.env.getProperty(FILE_NAME_MEDIUM));
    }
    
    @GetMapping(value = "/profilePicture/{employeeId}/big", produces = {"image/png", "image/jpg"})
    @ApiOperation(value = "Get big profile picture", code = 200)
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Get big profile picture")
    })
    public ResponseEntity getBigProfilePicture(@PathVariable("employeeId") final Long employeeId) throws IOException {
    	return this.getProfilePictureBySize(employeeId, this.env.getProperty(FILE_NAME_BIG));
    }
    
    /**
     * 
     * @param employeeId
     * @param fileSizeName
     * @return
     * @throws IOException
     */
    private ResponseEntity getProfilePictureBySize(final Long employeeId, final String fileSizeName) throws IOException {
    	final Employee employee = this.employeeService.get(employeeId);
    	
        if (null == employee || null == employee.getProfilePicture() || employee.getProfilePicture().isEmpty()) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
        
        final File file = new File(this.uploadService.getFilePath(employee.getId(), fileSizeName + employee.getProfilePicture().substring(employee.getProfilePicture().lastIndexOf("."))));
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
        
        final File file = new File(this.uploadService.getFilePath(employee.getId(), this.env.getProperty(FILE_NAME_PREVIEW) + ".jpg"));
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
