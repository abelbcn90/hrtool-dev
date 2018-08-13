package com.wedonegood.groups.calendar.rest.v1.dto;

import com.wedonegood.calendar.api.model.entity.Location;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Location")
public class LocationDto {
    @ApiModelProperty(position = 1, required = true, value = "Location ID", example = "1")
    private long id;
    @ApiModelProperty(position = 2, required = true, value = "Location name", example = "Spain / Madrid")
    private String name;

    public LocationDto(Location location) {
        this.id = location.getId();
        this.name = location.getName();
    }
}
