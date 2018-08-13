package com.wedonegood.groups.calendar.working.hours.rest.dto;

import com.wedonegood.working.hours.api.model.entity.WorkingHours;

import io.swagger.annotations.ApiModel;

@ApiModel("WorkingHours")
public class WorkingHoursDto {
    private Long id;
    private boolean active;
    private String name;
    private boolean monday;
    private boolean tuesday;
    private boolean wednesday;
    private boolean thursday;
    private boolean friday;
    private boolean saturday;
    private boolean sunday;
    private Integer entry;
    private Integer leave;
    private Integer lunchDuration;

    public WorkingHoursDto(WorkingHours workingHours) {
        this.id = workingHours.getId();
        this.active = workingHours.isActive();
        this.name = workingHours.getName();
        this.monday = workingHours.isMonday();
        this.tuesday = workingHours.isTuesday();
        this.wednesday = workingHours.isWednesday();
        this.thursday = workingHours.isThursday();
        this.friday = workingHours.isFriday();
        this.saturday = workingHours.isSaturday();
        this.sunday = workingHours.isSunday();
        this.entry = workingHours.getEntryMin();
        this.leave = workingHours.getLeaveMin();
        this.lunchDuration = workingHours.getLunchDuration();
    }

    public WorkingHours toWorkingHours() {
        WorkingHours workingHours = new WorkingHours();
        return toWorkingHours(workingHours);
    }

    public WorkingHours toWorkingHours(WorkingHours workingHours) {
        workingHours.setId(id);
        workingHours.setActive(active);
        workingHours.setName(name);
        workingHours.setMonday(monday);
        workingHours.setTuesday(tuesday);
        workingHours.setWednesday(wednesday);
        workingHours.setThursday(thursday);
        workingHours.setFriday(friday);
        workingHours.setSaturday(saturday);
        workingHours.setSunday(sunday);
        workingHours.setEntryMin(entry);
        workingHours.setLeaveMin(leave);
        workingHours.setLunchDuration(lunchDuration == null ? 0 : lunchDuration);
        return workingHours;
    }

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
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
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
	 * @return the entry
	 */
	public Integer getEntry() {
		return entry;
	}

	/**
	 * @param entry the entry to set
	 */
	public void setEntry(Integer entry) {
		this.entry = entry;
	}

	/**
	 * @return the leave
	 */
	public Integer getLeave() {
		return leave;
	}

	/**
	 * @param leave the leave to set
	 */
	public void setLeave(Integer leave) {
		this.leave = leave;
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
