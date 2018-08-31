package com.wedonegood.timesheet;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wedonegood.calendar.api.model.entity.Calendar;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Transactional
    public Event saveEvent(Event event) {
        event.addAudit();
        return eventRepository.save(event);
    }

    @Transactional
    public List<Event> saveEvents(List<Event> events) {
        events.forEach(Event::addAudit);
        return eventRepository.saveAll(events);
    }

    @Transactional
    public void deleteEvent(long eventId) {
        Event event = eventRepository.getOne(eventId);
        if(event == null) {
            return;
        }
        event.setActive(false);
        saveEvent(event);
    }

    public List<Event> getEvents(Calendar calendar) {
        return eventRepository.findEvents(calendar.getId(),
                calendar.getLocation().getId(),
                LocalDate.of(calendar.getYear(), 1, 1),
                LocalDate.of(calendar.getYear(), 12, 31));
    }

    public Integer countEvents(long locationId, int year) {
        List<Event> events = eventRepository.findEvents(null, locationId, LocalDate.of(year, 1, 1), LocalDate.of(year, 12, 31));
        int count = 0;
        for(Event e : events) {
            count += 1 + ChronoUnit.DAYS.between(e.getStartDate(), e.getEndDate());
        }
        return count;
    }
}
