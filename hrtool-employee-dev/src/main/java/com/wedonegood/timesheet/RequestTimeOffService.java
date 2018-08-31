package com.wedonegood.timesheet;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wedonegood.employee.api.model.entity.Employee;

public interface RequestTimeOffService {
    Page<RequestTimeOff> getRequestTimeOffs(Employee employee, Pageable pageable);
    List<RequestTimeOff> getRequestTimeOffs(Employee employee, int year, int month);
    Page<RequestTimeOff> getPendingRequestTimeOffs(long userId, Pageable pageable);
    RequestTimeOff getRequestTimeOff(long id);
    RequestTimeOff addRequestTimeOff(RequestTimeOff requestTimeOff);
    RequestTimeOff updateRequestTimeOff(RequestTimeOff requestTimeOff);
}
