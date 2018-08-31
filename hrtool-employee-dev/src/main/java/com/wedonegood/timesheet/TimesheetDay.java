package com.wedonegood.timesheet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TimesheetDay {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Timesheet timesheet;
    @Column(nullable = false)
    private int day;
    @ManyToOne(fetch = FetchType.EAGER)
    private RequestTimeOff requestTimeOff;
    @ManyToOne(fetch = FetchType.EAGER)
    private Event event;
    @Column
    private Integer entryMin;
    @Column
    private Integer leaveMin;
    @Column
    private Integer lunchDuration;
    @Column
    private Integer leaveReason;
    @Column(nullable = false)
    private boolean halfDay = false;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the timesheet
	 */
	public Timesheet getTimesheet() {
		return timesheet;
	}
	/**
	 * @param timesheet the timesheet to set
	 */
	public void setTimesheet(Timesheet timesheet) {
		this.timesheet = timesheet;
	}
	/**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}
	/**
	 * @param day the day to set
	 */
	public void setDay(int day) {
		this.day = day;
	}
	/**
	 * @return the requestTimeOff
	 */
	public RequestTimeOff getRequestTimeOff() {
		return requestTimeOff;
	}
	/**
	 * @param requestTimeOff the requestTimeOff to set
	 */
	public void setRequestTimeOff(RequestTimeOff requestTimeOff) {
		this.requestTimeOff = requestTimeOff;
	}
	/**
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}
	/**
	 * @param event the event to set
	 */
	public void setEvent(Event event) {
		this.event = event;
	}
	/**
	 * @return the entryMin
	 */
	public Integer getEntryMin() {
		return entryMin;
	}
	/**
	 * @param entryMin the entryMin to set
	 */
	public void setEntryMin(Integer entryMin) {
		this.entryMin = entryMin;
	}
	/**
	 * @return the leaveMin
	 */
	public Integer getLeaveMin() {
		return leaveMin;
	}
	/**
	 * @param leaveMin the leaveMin to set
	 */
	public void setLeaveMin(Integer leaveMin) {
		this.leaveMin = leaveMin;
	}
	/**
	 * @return the lunchDuration
	 */
	public Integer getLunchDuration() {
		return lunchDuration;
	}
	/**
	 * @param lunchDuration the lunchDuration to set
	 */
	public void setLunchDuration(Integer lunchDuration) {
		this.lunchDuration = lunchDuration;
	}
	/**
	 * @return the leaveReason
	 */
	public Integer getLeaveReason() {
		return leaveReason;
	}
	/**
	 * @param leaveReason the leaveReason to set
	 */
	public void setLeaveReason(Integer leaveReason) {
		this.leaveReason = leaveReason;
	}
	/**
	 * @return the halfDay
	 */
	public boolean isHalfDay() {
		return halfDay;
	}
	/**
	 * @param halfDay the halfDay to set
	 */
	public void setHalfDay(boolean halfDay) {
		this.halfDay = halfDay;
	}
    
    
}
