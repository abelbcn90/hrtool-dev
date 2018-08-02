package com.wedonegood.groups.calendar.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wedonegood.common.client.Client;
import com.wedonegood.groups.calendar.api.model.entity.Calendar;

public interface CalendarService {
    Page<Calendar> getCalendars(Pageable pageable);
    Page<Calendar> getCalendars(Client client, Pageable pageable);
    Calendar save(Calendar calendar);
    boolean delete(long calendarId);
    Calendar get(long calendarId);
}
