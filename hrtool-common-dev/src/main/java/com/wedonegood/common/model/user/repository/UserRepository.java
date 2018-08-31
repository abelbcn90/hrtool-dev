package com.wedonegood.common.model.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wedonegood.common.user.api.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByUserEmail(String email);
    
    @Query(value = "SELECT DISTINCT u.* " + 
    		"  FROM user u " + 
    		"  LEFT JOIN user_role ur ON ur.user_id = u.id " + 
    		"  LEFT JOIN role r ON r.id = ur.role_id " + 
    		"  LEFT JOIN role_function rf ON rf.role_id = r.id " + 
    		"  LEFT JOIN function f ON f.id = rf.function_id " + 
    		"    WHERE (f.name LIKE 'EMPLOYEES_VISUALIZATION' " + 
    		"      OR f.name LIKE 'TIMESHEET_EDITION' " + 
//    		"      OR f.name LIKE 'TIMESHEET_REMOVAL' " + 
    		"      OR f.name LIKE 'TIMESHEET_APPROVAL' " + 
    		"      OR f.name LIKE 'HOLIDAYS_APPROVAL') " +
    		"      AND u.active = true " +
    		"      AND u.client_id = ?1 " +
    		"  ORDER BY u.user_first_name, u.user_last_name ASC",
			nativeQuery = true)
	List<User> findManagers(final Long client);
}
