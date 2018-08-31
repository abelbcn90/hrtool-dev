package com.wedonegood.timesheet;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TimesheetDayRepository extends JpaRepository<TimesheetDay, Long> {
    List<TimesheetDay> findByTimesheetOrderByDayAsc(Timesheet timesheet);
}
