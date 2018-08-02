package com.wedonegood.roles.api;

import java.util.List;

import com.wedonegood.roles.api.model.entity.Function;

/**
 * @author Abel Pulido Ponce
 *
 */
public interface FunctionService {
	List<Function> getFunctions();
    Function get(final long functionId);
//    List<Function> list();
    List<String> findFunctionByUserId(final long userId);
}
