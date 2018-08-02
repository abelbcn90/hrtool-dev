package com.wedonegood.employee.api.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wedonegood.employee.api.model.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
    Page<Employee> findAllByActiveIsTrue(Pageable pageable);
    
    @Query(value = "SELECT e.* " +
    		" FROM employee e " +
    		" LEFT JOIN user u on e.user_id = u.id " +
    		" WHERE u.client_id = ? " +
    		" AND e.active = true " +
    		" ORDER BY u.user_first_name ASC #{#pageable}",
			nativeQuery = true)
	Page<Employee> findAllByClientAndActiveIsTrue(Long client, Pageable pageable);
    List<Employee> findEmployeeByActiveIsTrue();
//    Employee findEmployeeByUser_UserIdAndActiveIsTrue(long userId);
    Employee findEmployeeByUserIdAndActiveIsTrue(long userId);
    
    @Query(value = "SELECT COUNT(e.*) " +
    		"  FROM employee e " +
    		"  WHERE e.group_id = ? ",
			nativeQuery = true)
    Integer findNumberOfEmployeesByGroup(final long groupId);
}
