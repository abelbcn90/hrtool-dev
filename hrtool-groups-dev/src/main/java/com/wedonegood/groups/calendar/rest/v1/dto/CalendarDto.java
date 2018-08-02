package com.wedonegood.groups.calendar.rest.v1.dto;

import com.wedonegood.groups.calendar.api.model.entity.Calendar;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Calendar")
public class CalendarDto {
    @ApiModelProperty(position = 1, value = "Calendar ID", example = "1")
    private long id;
    @ApiModelProperty(position = 2, required = true, value = "Calendar name", example = "My first calendar")
    private String name;
    @ApiModelProperty(position = 3, required = true, value = "Year")
    private Integer year;
    @ApiModelProperty(position = 4, required = true, value = "Location")
    private LocationDto location;
    @ApiModelProperty(position = 5, required = true, value = "Holidays")
    private Integer holidays;

    public CalendarDto(Calendar calendar) {
        this.id = calendar.getId();
        this.name = calendar.getName();
        this.year = calendar.getYear();
        if(calendar.getLocation() != null) {
            this.location = new LocationDto(calendar.getLocation());
        }
    }

    public CalendarDto(Calendar calendar, Integer holidays) {
        this(calendar);
        this.holidays = holidays;
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

	/**
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * @return the location
	 */
	public LocationDto getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(LocationDto location) {
		this.location = location;
	}

	/**
	 * @return the holidays
	 */
	public Integer getHolidays() {
		return holidays;
	}

	/**
	 * @param holidays the holidays to set
	 */
	public void setHolidays(Integer holidays) {
		this.holidays = holidays;
	}
}
