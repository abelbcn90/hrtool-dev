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

import com.wedonegood.common.model.user.UserService;
import com.wedonegood.common.user.api.model.entity.User;
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

	private final static String FOLDER_HOME = "folder.home";
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
    
    @Autowired
    private UserService userService;

    @GetMapping(value = "/profilePicture/{userId}/small", produces = {"image/png", "image/jpeg"})
    @ApiOperation(value = "Get small profile picture", code = 200)
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Get small profile picture")
    })
    public ResponseEntity getSmallProfilePicture(@PathVariable("userId") final Long userId) throws IOException {
    	return this.getProfilePictureBySize(userId, this.env.getProperty(FILE_NAME_SMALL));
    }
    
    @GetMapping(value = "/profilePicture/{userId}/medium", produces = {"image/png", "image/jpeg"})
    @ApiOperation(value = "Get medium profile picture", code = 200)
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Get medium profile picture")
    })
    public ResponseEntity getMediumProfilePicture(@PathVariable("userId") final Long userId) throws IOException {
    	return this.getProfilePictureBySize(userId, this.env.getProperty(FILE_NAME_MEDIUM));
    }
    
    @GetMapping(value = "/profilePicture/{userId}/big", produces = {"image/png", "image/jpeg"})
    @ApiOperation(value = "Get big profile picture", code = 200)
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Get big profile picture")
    })
    public ResponseEntity getBigProfilePicture(@PathVariable("userId") final Long userId) throws IOException {
    	return this.getProfilePictureBySize(userId, this.env.getProperty(FILE_NAME_BIG));
    }
    
    /**
     * 
     * @param userId
     * @param fileSizeName
     * @return
     * @throws IOException
     */
    private ResponseEntity getProfilePictureBySize(final Long userId, final String fileSizeName) throws IOException {
    	final User user = this.userService.getUserByUserId(userId);
    	
        if (null == user || null == user.getProfilePicture() || user.getProfilePicture().isEmpty()) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
        
        final File file = new File(this.uploadService.getFilePathProfilePicture(userId, fileSizeName + user.getProfilePicture().substring(user.getProfilePicture().lastIndexOf("."))));
        final String contentType = Files.probeContentType(file.toPath());
        final InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        
        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
    
    @GetMapping(value = "/profilePicture/{userId}/full", produces = {"image/png", "image/jpeg"})
    @ApiOperation(value = "Get big profile picture", code = 200)
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Get full profile picture")
    })
    public ResponseEntity getProfilePictureFull(@PathVariable("userId") final Long userId) throws IOException {
    	final User user = this.userService.getUserByUserId(userId);
    	
    	if (null == user || null == user.getProfilePicture() || user.getProfilePicture().isEmpty()) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    	
    	final File file = new File(this.env.getProperty(FOLDER_HOME) + user.getProfilePicture());
    	final String contentType = Files.probeContentType(file.toPath());
    	final InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
    	
    	return ResponseEntity.ok()
    			.contentLength(file.length())
    			.contentType(MediaType.parseMediaType(contentType))
    			.body(resource);
    }
    
    @GetMapping(value = "/passportScan/{employeeId}/preview", produces = {"image/png", "image/jpeg", "application/pdf"})
    @ApiOperation(value = "Get passport scan preview", code = 200)
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Get passport scan preview")
    })
    public ResponseEntity getPassportScanPreview(@PathVariable("employeeId") final Long employeeId) throws IOException {
    	final Employee employee = this.employeeService.get(employeeId);
    	
        if (null == employee || null == employee.getPassportScan() || employee.getPassportScan().isEmpty()) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
        
        final File file = new File(this.uploadService.getFilePathPassportScan(employee.getId(), this.env.getProperty(FILE_NAME_PREVIEW) + "." + com.google.common.net.MediaType.PNG.subtype()));
        final String contentType = Files.probeContentType(file.toPath());
        final InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        
        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
    
    @GetMapping(value = "/passportScan/{employeeId}/full", produces = {"image/png", "image/jpeg", "application/pdf"})
    @ApiOperation(value = "Get passport scan", code = 200)
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Get passport scan full")
    })
    public ResponseEntity getPassportScanFull(@PathVariable("employeeId") final Long employeeId) throws IOException {
    	final Employee employee = this.employeeService.get(employeeId);
    	
    	if (null == employee || null == employee.getPassportScan() || employee.getPassportScan().isEmpty()) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    	
    	final File file = new File(this.env.getProperty(FOLDER_HOME) + employee.getPassportScan());
    	final String contentType = Files.probeContentType(file.toPath());
    	final InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
    	
    	return ResponseEntity.ok()
    			.contentLength(file.length())
    			.contentType(MediaType.parseMediaType(contentType))
    			.body(resource);
    }
}
