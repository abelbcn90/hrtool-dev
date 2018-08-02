package com.wedonegood.roles.rest.v1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wedonegood.roles.api.FunctionService;
import com.wedonegood.roles.api.model.entity.Function;
import com.wedonegood.roles.rest.common.PagingController;
import com.wedonegood.roles.rest.v1.dto.FunctionDto;

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
@RequestMapping(value = "/api/v1/function")
@Api(value="Function", description="Operations pertaining to Functions", position = 3)
public class FunctionController extends PagingController {
	
	@Autowired
	private FunctionService functionService;
	
	@GetMapping("/list")
    @ApiOperation(value = "Get functions", nickname = "getFunctions")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of functions", response = FunctionDto.class, responseContainer = "List")
    })
    public ResponseEntity<List<FunctionDto>> getFunctions() {
        final List<Function> functionList = this.functionService.getFunctions();
        
        if (null == functionList) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        final List<FunctionDto> functionDtoList = new ArrayList<FunctionDto>();
        for (final Function f : functionList) {
        	functionDtoList.add(new FunctionDto(f));
        }
        
        return ResponseEntity.ok(functionDtoList);
    }
	
	@GetMapping(value = "/{functionId}", produces = "application/json")
    @ApiOperation(value = "Get full Function data", nickname = "getFunctionById")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Function", response = FunctionDto.class)
    })
    public ResponseEntity<FunctionDto> getFunction(@PathVariable("functionId") final Long functionId) {
    	final Function function = this.functionService.get(functionId);
        
        if (null == function) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        return ResponseEntity.ok(new FunctionDto(function));
    }
}
