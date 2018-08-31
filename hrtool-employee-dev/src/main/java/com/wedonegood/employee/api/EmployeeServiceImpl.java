package com.wedonegood.employee.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wedonegood.employee.api.model.entity.Employee;
import com.wedonegood.employee.api.model.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Override
    public Page<Employee> getEmployees(Pageable pageable) {
        return this.employeeRepository.findAllByActiveIsTrue(pageable);
    }

    @Override
    public Page<Employee> getEmployees(Long client, Pageable pageable) {
        return this.employeeRepository.findAllByClientAndActiveIsTrue(client, pageable);
    }

    @Override
    public Page<Employee> getEmployeesByManager(long managerId, Pageable pageable) {
        return this.employeeRepository.findAllByManagerAndActiveIsTrue(managerId, pageable);
    }
    
    @Override
    public Page<Employee> searchEmployees(final Long client, final String keyword, final Pageable pageable) {
    	return this.employeeRepository.searchEmployees(client, keyword, pageable);
    }

    public List<Employee> getActiveEmployee() {
        return employeeRepository.findEmployeeByActiveIsTrue();
    }

    public Employee getEmployee(long userId) {
//        return employeeRepository.findEmployeeByUser_UserIdAndActiveIsTrue(userId);
        return employeeRepository.findEmployeeByUserIdAndActiveIsTrue(userId);
    }

    public Employee getEmployeeById(long employeeId) {
//        return employeeRepository.findEmployeeByUser_UserIdAndActiveIsTrue(userId);
        return employeeRepository.findEmployeeByIdAndActiveIsTrue(employeeId);
    }
    
    @Override
    public Employee save(final Employee employee) {
        employee.addAudit();
        
        return this.employeeRepository.saveAndFlush(employee);
    }
    
    @Override
    public Integer findNumberOfEmployeesByGroup(final long groupId) {
    	return this.employeeRepository.findNumberOfEmployeesByGroup(groupId);
    }
    
    @Override
    public Employee get(final long employeeId) {
    	return this.employeeRepository.getOne(employeeId);
    }
    
    @Override
    public List<Employee> findAllByGroupIdAndActiveIsTrue(final Long groupId) {
    	return this.employeeRepository.findAllByGroupIdAndActiveIsTrue(groupId);
    }
}
