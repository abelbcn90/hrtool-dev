package com.wedonegood.groups.calendar.api.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wedonegood.common.client.Client;
import com.wedonegood.groups.calendar.api.model.entity.Calendar;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    Page<Calendar> findAllByClientAndActiveIsTrue(Client client, Pageable pageable);
    Page<Calendar> findAllByActiveIsTrue(Pageable pageable);
}
