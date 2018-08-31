package com.wedonegood.timesheet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.wedonegood.calendar.api.model.entity.Calendar;
import com.wedonegood.common.model.common.BaseEntity;
import com.wedonegood.employee.api.model.entity.Employee;
import com.wedonegood.working.hours.api.model.entity.WorkingHours;

@Entity
public class Timesheet extends BaseEntity {
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Employee employee;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private TimesheetStatus timesheetStatus;
    @Column(nullable = false)
    private int year;
    @Column(nullable = false)
    private int month;
    @ManyToOne(fetch = FetchType.LAZY)
    private Calendar calendar;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private WorkingHours workingHours;
	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}
	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	/**
	 * @return the timesheetStatus
	 */
	public TimesheetStatus getTimesheetStatus() {
		return timesheetStatus;
	}
	/**
	 * @param timesheetStatus the timesheetStatus to set
	 */
	public void setTimesheetStatus(TimesheetStatus timesheetStatus) {
		this.timesheetStatus = timesheetStatus;
	}
	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(int month) {
		this.month = month;
	}
	/**
	 * @return the calendar
	 */
	public Calendar getCalendar() {
		return calendar;
	}
	/**
	 * @param calendar the calendar to set
	 */
	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}
	/**
	 * @return the workingHours
	 */
	public WorkingHours getWorkingHours() {
		return workingHours;
	}
	/**
	 * @param workingHours the workingHours to set
	 */
	public void setWorkingHours(WorkingHours workingHours) {
		this.workingHours = workingHours;
	}
    
    
}
