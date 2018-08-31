package com.wedonegood.timesheet;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.wedonegood.common.model.common.BaseEntity;
import com.wedonegood.common.user.api.model.entity.User;
import com.wedonegood.employee.api.model.entity.Employee;

@Entity
public class RequestTimeOff extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private RequestTimeOffType requestTimeOffType;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private RequestTimeOffStatus requestTimeOffStatus;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Employee employee;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    private String message;

    private String reply;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    private User manager;

    private boolean readed;

	/**
	 * @return the requestTimeOffType
	 */
	public RequestTimeOffType getRequestTimeOffType() {
		return requestTimeOffType;
	}

	/**
	 * @param requestTimeOffType the requestTimeOffType to set
	 */
	public void setRequestTimeOffType(RequestTimeOffType requestTimeOffType) {
		this.requestTimeOffType = requestTimeOffType;
	}

	/**
	 * @return the requestTimeOffStatus
	 */
	public RequestTimeOffStatus getRequestTimeOffStatus() {
		return requestTimeOffStatus;
	}

	/**
	 * @param requestTimeOffStatus the requestTimeOffStatus to set
	 */
	public void setRequestTimeOffStatus(RequestTimeOffStatus requestTimeOffStatus) {
		this.requestTimeOffStatus = requestTimeOffStatus;
	}

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
	 * @return the startDate
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the reply
	 */
	public String getReply() {
		return reply;
	}

	/**
	 * @param reply the reply to set
	 */
	public void setReply(String reply) {
		this.reply = reply;
	}

	/**
	 * @return the manager
	 */
	public User getManager() {
		return manager;
	}

	/**
	 * @param manager the manager to set
	 */
	public void setManager(User manager) {
		this.manager = manager;
	}

	/**
	 * @return the readed
	 */
	public boolean isReaded() {
		return readed;
	}

	/**
	 * @param readed the readed to set
	 */
	public void setReaded(boolean readed) {
		this.readed = readed;
	}
    
    
}
