package com.wedonegood.timesheet;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wedonegood.employee.api.model.entity.Employee;

@Service
public class RequestTimeOffServiceImpl implements RequestTimeOffService {

    @Autowired
    private RequestTimeOffRepository requestTimeOffRepository;

    @Autowired
    private TimesheetService timesheetService;

    @Autowired
    private TimesheetDayService timesheetDayService;

    public Page<RequestTimeOff> getRequestTimeOffs(Employee employee, Pageable pageable) {
        return requestTimeOffRepository.findByEmployeeOrderByStartDateDesc(employee, pageable);
    }

    public Page<RequestTimeOff> getPendingRequestTimeOffs(long userId, Pageable pageable) {
        return requestTimeOffRepository.findPendingRequestTimeOff(userId, pageable);
    }

    public List<RequestTimeOff> getRequestTimeOffs(Employee employee, int year, int month) {
        LocalDate begin =  LocalDate.of(year, month, 1);
        LocalDate end =  LocalDate.of(year, month + 1, 1);
        return requestTimeOffRepository.findByEmployee(employee, begin, end);
    }

    public RequestTimeOff getRequestTimeOff(long id) {
        return requestTimeOffRepository.findByIdAndActiveIsTrue(id);
    }

    @Transactional
    public RequestTimeOff addRequestTimeOff(RequestTimeOff requestTimeOff) {
        requestTimeOff.addAudit();
        requestTimeOff = requestTimeOffRepository.save(requestTimeOff);
        int startYear = requestTimeOff.getStartDate().getYear();
        int endYear = requestTimeOff.getEndDate().getYear();
        for(int year = startYear; year <= endYear; year++) {
            int startMonth = (year == startYear) ? requestTimeOff.getStartDate().getMonthValue() : 1;
            int endMonth = (year == endYear) ? requestTimeOff.getEndDate().getMonthValue() : 12;
            for (int month = startMonth; month <= endMonth; month++) {
                Timesheet t = timesheetService.getTimesheet(requestTimeOff.getEmployee(), year, month);
                if(t != null && t.getTimesheetStatus().getId() != TimesheetStatusEnum.APPROVE.getId()) {
                    List<TimesheetDay> days = timesheetDayService.getTimesheetDays(t);
                    int beginDay = year == startYear && month == startMonth ? requestTimeOff.getStartDate().getDayOfMonth() : 1;
                    int endDay = year == endYear && month == endMonth ? requestTimeOff.getEndDate().getDayOfMonth() : 31;
                    for(TimesheetDay d : days) {
                        if(d.getDay() >= beginDay && d.getDay() <= endDay) {
                            d.setRequestTimeOff(requestTimeOff);
                        }
                    }
                    timesheetDayService.updateTimesheetDays(days);
                }
            }
        }
        return requestTimeOff;
    }

    @Transactional
    public RequestTimeOff updateRequestTimeOff(RequestTimeOff requestTimeOff) {
        requestTimeOff.addAudit();
        return requestTimeOffRepository.save(requestTimeOff);
    }
}
