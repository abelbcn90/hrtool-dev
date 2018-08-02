package com.wedonegood.roles.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wedonegood.roles.api.model.entity.Function;
import com.wedonegood.roles.api.model.repository.FunctionRepository;

/**
 * 
 * @author Abel Pulido Ponce
 *
 */
@Service
public class FunctionServiceImpl implements FunctionService {

    @Autowired
    private FunctionRepository functionRepository;
    
    @Override
    public List<Function> getFunctions() {
        return this.functionRepository.findAll();
    }
    
    @Override
    public Function get(final long functionId) {
        return this.functionRepository.getOne(functionId);
    }
    
//    @Override
//    public List<Function> list() {
//        return this.functionRepository.findAll();
//    }
    
    @Override
	public List<String> findFunctionByUserId(final long userId) {
		return this.functionRepository.findFunctionByUserId(userId);
	}
}
