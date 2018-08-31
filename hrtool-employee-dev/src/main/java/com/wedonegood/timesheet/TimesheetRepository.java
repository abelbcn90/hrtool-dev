package com.wedonegood.timesheet;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wedonegood.employee.api.model.entity.Employee;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
    Timesheet findByEmployeeAndYearAndMonthAndActiveIsTrue(Employee employee, int year, int month);
    List<Timesheet> findByEmployeeAndActiveIsTrueOrderByYearDescMonthDesc(Employee employee);
    @Query("select t from Timesheet t where t.active = true and t.timesheetStatus.id = 1 and t.employee.manager.id = ?1")
    List<Timesheet> findPendingTimesheets(long userId);
}
