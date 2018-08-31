package com.wedonegood.timesheet;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TimesheetMsgRepository extends JpaRepository<TimesheetMsg, Long> {
    List<TimesheetMsg> findAllByTimesheetOrderByDateAsc(Timesheet timesheet);
}
