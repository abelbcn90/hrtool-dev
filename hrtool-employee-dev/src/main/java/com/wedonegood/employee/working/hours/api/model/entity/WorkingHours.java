package com.wedonegood.employee.working.hours.api.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.wedonegood.employee.model.common.BaseEntity;

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
    
    
}
