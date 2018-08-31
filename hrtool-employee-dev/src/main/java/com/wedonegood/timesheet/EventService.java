package com.wedonegood.timesheet;

import java.util.List;

import com.wedonegood.calendar.api.model.entity.Calendar;

public interface EventService {
    Event saveEvent(Event event);
    List<Event> saveEvents(List<Event> events);
    void deleteEvent(long calendarDayId);
    List<Event> getEvents(Calendar calendar);
    Integer countEvents(long locationId, int year);
}
