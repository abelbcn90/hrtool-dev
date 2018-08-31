package com.wedonegood.timesheet;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wedonegood.working.hours.api.model.entity.WorkingHours;

@Service
public class TimesheetDayServiceImpl implements TimesheetDayService {

    @Autowired
    private TimesheetDayRepository timesheetDayRepository;

    @Autowired
    private RequestTimeOffService requestTimeOffService;

    @Autowired
    private EventService eventService;

    public List<TimesheetDay> getTimesheetDays(Timesheet timesheet) {
        return timesheetDayRepository.findByTimesheetOrderByDayAsc(timesheet);
    }

    public void updateTimesheetDays(List<TimesheetDay> timesheetDays) {
        timesheetDayRepository.saveAll(timesheetDays);
    }

    @Transactional
    public void generate(List<Timesheet> timesheets) {
        List<TimesheetDay> res = new ArrayList<>();
        for(Timesheet t: timesheets) {
            List<RequestTimeOff> requestTimeOffs = requestTimeOffService.getRequestTimeOffs(t.getEmployee(), t.getYear(), t.getMonth());
            LocalDate begin = LocalDate.of(t.getYear(), t.getMonth(), 1);
            LocalDate end = begin.with(lastDayOfMonth());
            List<Event> events = eventService.getEvents(t.getCalendar());
            for(int i = 1; i <= end.getDayOfMonth(); i++) {
                LocalDate day = LocalDate.of(t.getYear(), t.getMonth(), i);
                TimesheetDay timesheetDay = new TimesheetDay();
                res.add(timesheetDay);
                timesheetDay.setTimesheet(t);
                timesheetDay.setDay(i);
                Optional<RequestTimeOff> requestTimeOff = requestTimeOffs.stream()
                        .filter(rto -> day.compareTo(rto.getStartDate()) >= 0 && day.compareTo(rto.getEndDate()) <= 0)
                        .findFirst();
                if(requestTimeOff.isPresent()) {
                    timesheetDay.setRequestTimeOff(requestTimeOff.get());
                } else {
                    Optional<Event> event = events.stream()
                            .filter(e -> day.compareTo(e.getStartDate()) >= 0 && day.compareTo(e.getEndDate()) <= 0)
                            .findFirst();
                    if(event.isPresent()) {
                        timesheetDay.setEvent(event.get());
                    } else {
                        WorkingHours wh = t.getEmployee().getGroup().getWorkingHours();
                        if(wh.isWorkDay(day.getDayOfWeek().getValue())) {
                            timesheetDay.setEntryMin(wh.getEntryMin());
                            timesheetDay.setLeaveMin(wh.getLeaveMin());
                            timesheetDay.setLunchDuration(wh.getLunchDuration());
                        }
                    }
                }
            }
        }
        timesheetDayRepository.saveAll(res);
    }


}
