package com.wedonegood.working.hours.api.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.wedonegood.common.model.common.BaseEntity;

@Entity
public class WorkingHours extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private boolean monday;
    @Column(nullable = false)
    private boolean tuesday;
    @Column(nullable = false)
    private boolean wednesday;
    @Column(nullable = false)
    private boolean thursday;
    @Column(nullable = false)
    private boolean friday;
    @Column(nullable = false)
    private boolean saturday;
    @Column(nullable = false)
    private boolean sunday;
    @Column(nullable = false)
    private Integer entryMin;
    @Column(nullable = false)
    private Integer leaveMin;
    @Column(nullable = false)
    private Integer lunchDuration;

    public boolean isWorkDay(int day) {
        switch (day) {
            case 0: return sunday;
            case 1: return monday;
            case 2: return tuesday;
            case 3: return wednesday;
            case 4: return thursday;
            case 5: return friday;
            case 6: return saturday;
            case 7: return sunday;
            default: throw new IllegalArgumentException();
        }
    }

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the monday
	 */
	public boolean isMonday() {
		return monday;
	}

	/**
	 * @param monday the monday to set
	 */
	public void setMonday(boolean monday) {
		this.monday = monday;
	}

	/**
	 * @return the tuesday
	 */
	public boolean isTuesday() {
		return tuesday;
	}

	/**
	 * @param tuesday the tuesday to set
	 */
	public void setTuesday(boolean tuesday) {
		this.tuesday = tuesday;
	}

	/**
	 * @return the wednesday
	 */
	public boolean isWednesday() {
		return wednesday;
	}

	/**
	 * @param wednesday the wednesday to set
	 */
	public void setWednesday(boolean wednesday) {
		this.wednesday = wednesday;
	}

	/**
	 * @return the thursday
	 */
	public boolean isThursday() {
		return thursday;
	}

	/**
	 * @param thursday the thursday to set
	 */
	public void setThursday(boolean thursday) {
		this.thursday = thursday;
	}

	/**
	 * @return the friday
	 */
	public boolean isFriday() {
		return friday;
	}

	/**
	 * @param friday the friday to set
	 */
	public void setFriday(boolean friday) {
		this.friday = friday;
	}

	/**
	 * @return the saturday
	 */
	public boolean isSaturday() {
		return saturday;
	}

	/**
	 * @param saturday the saturday to set
	 */
	public void setSaturday(boolean saturday) {
		this.saturday = saturday;
	}

	/**
	 * @return the sunday
	 */
	public boolean isSunday() {
		return sunday;
	}

	/**
	 * @param sunday the sunday to set
	 */
	public void setSunday(boolean sunday) {
		this.sunday = sunday;
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
    
    
}
