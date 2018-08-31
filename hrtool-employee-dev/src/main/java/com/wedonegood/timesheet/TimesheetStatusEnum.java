package com.wedonegood.timesheet;

public enum TimesheetStatusEnum {
    PENDING(0, "Pending"),
    SENT(1, "Sent"),
    APPROVE(2, "Approved"),
    REFUSE(3, "Refused"),
    ;

    private long id;
    private String name;

    TimesheetStatusEnum(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public TimesheetStatus toTimesheetStatus() {
        TimesheetStatus timesheetStatus = new TimesheetStatus();
        timesheetStatus.setId(id);
        timesheetStatus.setName(name);
        return timesheetStatus;
    }

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
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
    
    
}
