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

    public List<Employee> getActiveEmployee() {
        return employeeRepository.findEmployeeByActiveIsTrue();
    }

    public Employee getEmployee(long userId) {
//        return employeeRepository.findEmployeeByUser_UserIdAndActiveIsTrue(userId);
        return employeeRepository.findEmployeeByUserIdAndActiveIsTrue(userId);
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
}
