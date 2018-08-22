package com.wedonegood.employee.api;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wedonegood.employee.api.model.entity.Employee;

public interface EmployeeService {
	Page<Employee> getEmployees(Pageable pageable);
    Page<Employee> getEmployees(Long client, Pageable pageable);
    Page<Employee> searchEmployees(final Long client, final String keyword, final Pageable pageable);
    List<Employee> getActiveEmployee();
    Employee getEmployee(long userId);
    Employee save(final Employee employee);
    Integer findNumberOfEmployeesByGroup(final long groupId);
    Employee get(final long employeeId);
    List<Employee> findAllByGroupIdAndActiveIsTrue(final Long groupId);
}
