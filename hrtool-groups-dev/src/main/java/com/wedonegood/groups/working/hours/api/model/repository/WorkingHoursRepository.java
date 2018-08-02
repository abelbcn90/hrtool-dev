package com.wedonegood.groups.working.hours.api.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wedonegood.groups.working.hours.api.model.entity.WorkingHours;

import java.util.List;

public interface WorkingHoursRepository extends JpaRepository<WorkingHours, Long> {
    List<WorkingHours> findAllByActiveIsTrue();
}
