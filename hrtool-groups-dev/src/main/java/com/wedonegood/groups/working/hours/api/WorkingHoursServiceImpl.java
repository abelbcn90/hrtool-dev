package com.wedonegood.groups.working.hours.api;

import com.wedonegood.groups.working.hours.api.model.entity.WorkingHours;
import com.wedonegood.groups.working.hours.api.model.repository.WorkingHoursRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class WorkingHoursServiceImpl implements WorkingHoursService {

    @Autowired
    private WorkingHoursRepository workingHoursRepository;

    public List<WorkingHours> getWorkingHours() {
        return workingHoursRepository.findAllByActiveIsTrue();
    }

    public WorkingHours getWorkingHours(long id) {
        return workingHoursRepository.getOne(id);
    }

    @Transactional
    public WorkingHours save(WorkingHours workingHours) {
        workingHours.addAudit();
        return workingHoursRepository.save(workingHours);
    }

    @Transactional
    public void delete(WorkingHours workingHours) {
        workingHours.setActive(false);
        save(workingHours);
    }
}
