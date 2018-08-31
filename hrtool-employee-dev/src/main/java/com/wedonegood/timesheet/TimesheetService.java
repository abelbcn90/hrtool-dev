package com.wedonegood.timesheet;

import java.util.List;

import com.wedonegood.employee.api.model.entity.Employee;

public interface TimesheetService {
    List<Timesheet> getTimesheets(Employee employee);
    Timesheet getTimesheet(Employee employee, int year, int month);
    List<Timesheet> getPendingTimesheets(long userId);
    Timesheet getTimesheet(long id);
    Timesheet updateTimesheet(Timesheet timesheet);
    void addMsg(Timesheet timesheet, String msg);
    List<TimesheetMsg> getMsgs(Timesheet timesheet);
    Timesheet createIfNotExistAndSave(Employee employee, int year, int month);
}
