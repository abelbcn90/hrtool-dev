package com.wedonegood.groups.working.hours.api.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wedonegood.working.hours.api.model.entity.WorkingHours;

public interface WorkingHoursRepository extends JpaRepository<WorkingHours, Long> {
    List<WorkingHours> findAllByActiveIsTrue();
}
