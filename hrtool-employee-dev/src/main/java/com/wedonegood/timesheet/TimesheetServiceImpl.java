package com.wedonegood.timesheet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wedonegood.common.model.user.UserService;
import com.wedonegood.common.security.UserInfoContext;
import com.wedonegood.common.user.api.model.entity.User;
import com.wedonegood.employee.api.EmployeeService;
import com.wedonegood.employee.api.model.entity.Employee;

@Service
public class TimesheetServiceImpl implements TimesheetService {

    @Autowired
    private TimesheetRepository timesheetRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TimesheetDayService timesheetDayService;

    @Autowired
    private TimesheetMsgRepository timesheetMsgRepository;

    @Autowired
    private UserService userService;


    @Transactional
    public void generate(int year, int month) {
        List<Employee> employees = employeeService.getActiveEmployee();
        final List<Timesheet> timesheets = new ArrayList<>();
        employees.forEach(e -> {
            Timesheet timesheet = createIfNotExist(e, year, month);
            if(timesheet != null) {
                timesheets.add(timesheet);
            }
        });
        if(timesheets.size() > 0) {
            List<Timesheet> res = timesheetRepository.saveAll(timesheets);
            timesheetDayService.generate(res);
        }
    }

    @Transactional
    public Timesheet createIfNotExistAndSave(Employee employee, int year, int month) {
        Timesheet timesheet = createIfNotExist(employee, year, month);
        if(timesheet != null) {
            return timesheetRepository.save(timesheet);
        }
        return null;
    }

    private Timesheet createIfNotExist(Employee employee, int year, int month) {
        Timesheet timesheet = getTimesheet(employee, year, month);
        if(timesheet == null) {

            if(employee.getGroup().getCalendar().getYear() == year) {
                return createNew(employee, year, month);
            }
        }
        return null;
    }

    public Timesheet getTimesheet(Employee employee, int year, int month) {
        return timesheetRepository.findByEmployeeAndYearAndMonthAndActiveIsTrue(employee, year, month);
    }

    public Timesheet getTimesheet(long id) {
        return timesheetRepository.findById(id).orElse(null);
    }

    @Transactional
    public void addMsg(Timesheet timesheet, String msg) {
        TimesheetMsg m = new TimesheetMsg();
        m.setTimesheet(timesheet);
        m.setMessage(msg);
        m.setDate(LocalDateTime.now());
        User user = userService.getUserByUserId(UserInfoContext.getCurrent().getUserId());
        m.setUser(user);
        timesheetMsgRepository.save(m);
    }

    public List<TimesheetMsg> getMsgs(Timesheet timesheet) {
        return timesheetMsgRepository.findAllByTimesheetOrderByDateAsc(timesheet);
    }

    @Transactional
    public Timesheet updateTimesheet(Timesheet timesheet) {
        timesheet.addAudit();
        return timesheetRepository.save(timesheet);
    }

    private Timesheet createNew(Employee employee, int year, int month) {
        Timesheet timesheet = new Timesheet();
        timesheet.setTimesheetStatus(TimesheetStatusEnum.PENDING.toTimesheetStatus());
        timesheet.setEmployee(employee);
        timesheet.setCalendar(employee.getGroup().getCalendar());
        timesheet.setYear(year);
        timesheet.setMonth(month);
        timesheet.setActive(true);
        timesheet.setCreatedDate(LocalDateTime.now());
        timesheet.setWorkingHours(employee.getGroup().getWorkingHours());
        UserInfoContext uic = UserInfoContext.getCurrent();
        timesheet.addAudit(uic == null ? "system" : uic.getUserEmail());
        return timesheet;
    }

    public List<Timesheet> getTimesheets(Employee employee) {
        return timesheetRepository.findByEmployeeAndActiveIsTrueOrderByYearDescMonthDesc(employee);
    }

    public List<Timesheet> getPendingTimesheets(long userId) {
        return timesheetRepository.findPendingTimesheets(userId);
    }
}
