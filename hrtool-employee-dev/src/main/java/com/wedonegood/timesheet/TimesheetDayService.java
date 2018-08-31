package com.wedonegood.timesheet;

import java.util.List;

public interface TimesheetDayService {
    void generate(List<Timesheet> timesheets);
    List<TimesheetDay> getTimesheetDays(Timesheet timesheet);
    void updateTimesheetDays(List<TimesheetDay> timesheetDays);
}
