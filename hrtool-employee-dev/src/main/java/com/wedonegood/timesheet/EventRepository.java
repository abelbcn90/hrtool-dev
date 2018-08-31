package com.wedonegood.timesheet;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<Event, Long>  {

    @Query("select cd from Event cd where cd.active = true and (" +
                "( cd.calendar is not null and cd.calendar.id = ?1 ) or " +
                "( cd.location is not null and cd.location.id = ?2 and cd.startDate >= ?3 and cd.startDate <= ?4) " +
            ")")
    List<Event> findEvents(Long calendarId, Long locationId, LocalDate startDate, LocalDate endDate);

}
