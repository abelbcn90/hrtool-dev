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
    		" LEFT JOIN user u ON e.user_id = u.id " +
    		" WHERE u.client_id = ? " +
    		" AND e.active = true " +
    		" ORDER BY u.user_first_name, u.user_last_name ASC #{#pageable}",
			nativeQuery = true)
	Page<Employee> findAllByClientAndActiveIsTrue(Long client, Pageable pageable);
    
    @Query(value = "SELECT e.* " +
    		" FROM employee e " +
    		" LEFT JOIN user u ON e.user_id = u.id " +
    		" LEFT JOIN groups g ON e.group_id = g.id " +
    		" WHERE u.client_id = ?1 " +
    		" AND e.job_position LIKE %?2% " +
    		" OR e.nin LIKE %?2% " +
    		" OR e.number LIKE %?2% " +
    		" OR u.user_first_name LIKE %?2% " +
    		" OR u.user_last_name LIKE %?2% " +
    		" OR g.name LIKE %?2% " +
    		" AND e.active = true " +
    		" ORDER BY u.user_first_name, u.user_last_name ASC #{#pageable}",
			nativeQuery = true)
    Page<Employee> searchEmployees(final Long client, final String keyword, final Pageable pageable);
    
    List<Employee> findEmployeeByActiveIsTrue();
//    Employee findEmployeeByUser_UserIdAndActiveIsTrue(long userId);
    Employee findEmployeeByUserIdAndActiveIsTrue(long userId);
    
    @Query(value = "SELECT COUNT(e.*) " +
    		"  FROM employee e " +
    		"  WHERE e.group_id = ? ",
			nativeQuery = true)
    Integer findNumberOfEmployeesByGroup(final long groupId);
    
    List<Employee> findAllByGroupIdAndActiveIsTrue(final Long groupId);
}
