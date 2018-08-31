package com.wedonegood.timesheet;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wedonegood.employee.api.model.entity.Employee;

public interface RequestTimeOffRepository extends JpaRepository<RequestTimeOff, Long> {
    Page<RequestTimeOff> findByEmployeeOrderByStartDateDesc(Employee employee, Pageable pageable);
    RequestTimeOff findByIdAndActiveIsTrue(long id);
    @Query("select rto from RequestTimeOff rto where rto.employee = ?1 and (rto.startDate between ?2 and ?3 or rto.endDate between ?2 and ?3)")
    List<RequestTimeOff> findByEmployee(Employee employee, LocalDate b, LocalDate e);
    @Query("select rto from RequestTimeOff rto where rto.employee.manager.id = ?1 and rto.requestTimeOffStatus.id = 0 and rto.active = true")
    Page<RequestTimeOff> findPendingRequestTimeOff(long userId, Pageable pageable);
}
