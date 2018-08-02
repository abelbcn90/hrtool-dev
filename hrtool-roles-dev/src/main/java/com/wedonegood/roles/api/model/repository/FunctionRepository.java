package com.wedonegood.roles.api.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wedonegood.roles.api.model.entity.Function;

/**
 * @author Abel Pulido Ponce
 *
 */
@Repository
public interface FunctionRepository extends JpaRepository<Function, Long> {
	
	@Query(value = "SELECT DISTINCT(f.name)" + 
			"  FROM function f" + 
			"    LEFT JOIN role_function rf ON rf.function_id = f.id" + 
			"    LEFT JOIN role r ON r.id = rf.role_id" + 
			"    LEFT JOIN user_role ur ON ur.role_id = r.id" + 
			"  WHERE ur.user_id = ? AND r.active = true", 
			nativeQuery = true)
    List<String> findFunctionByUserId(final long userId);
}
