package com.wedonegood.employee.rest.v1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wedonegood.common.model.user.UserService;
import com.wedonegood.common.security.UserInfoContext;
import com.wedonegood.common.user.api.model.entity.User;
import com.wedonegood.common.user.rest.dto.UserDto;

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
@RequestMapping(value = "/api/v1/user")
@Api(value="User", description="Operations pertaining to Users", position = 3)
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/managers")
    @ApiOperation(value = "Get managers", nickname = "getManagers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of managers", response = UserDto.class, responseContainer = "List")
    })
    public ResponseEntity<List<UserDto>> getManagers() {
		final List<User> managers = this.userService.findManagers(UserInfoContext.getCurrent().getClientId());
		
		if (null == managers) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
		
		final List<UserDto> managersDto = new ArrayList<UserDto>();
		
		for (final User user : managers) {
			managersDto.add(new UserDto(user));
		}
        
        return ResponseEntity.ok(managersDto);
    }
}
