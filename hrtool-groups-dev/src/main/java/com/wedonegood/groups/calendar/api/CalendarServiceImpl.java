package com.wedonegood.groups.calendar.api;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wedonegood.groups.calendar.api.model.entity.Calendar;
import com.wedonegood.groups.calendar.api.model.repository.CalendarRepository;
import com.wedonegood.groups.client.entity.Client;

@Service
public class CalendarServiceImpl implements CalendarService {

    @Autowired
    private CalendarRepository calendarRepository;

    public Page<Calendar> getCalendars(Pageable pageable) {
        return calendarRepository.findAllByActiveIsTrue(pageable);
    }

    public Page<Calendar> getCalendars(Client client, Pageable pageable) {
        return calendarRepository.findAllByClientAndActiveIsTrue(client, pageable);
    }

    @Transactional
    public Calendar save(Calendar calendar) {
        calendar.addAudit();
        return calendarRepository.save(calendar);
    }

    public Calendar get(long calendarId) {
        return calendarRepository.getOne(calendarId);
    }

    @Transactional
    public boolean delete(long calendarId) {
        Calendar calendar = get(calendarId);
        if(calendar == null) {
            return false;
        }
        calendar.setActive(false);
        save(calendar);
        return true;
    }
}
