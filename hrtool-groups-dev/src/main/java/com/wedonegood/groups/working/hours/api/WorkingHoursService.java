package com.wedonegood.groups.working.hours.api;

import java.util.List;

import com.wedonegood.groups.working.hours.api.model.entity.WorkingHours;

public interface WorkingHoursService {
   List<WorkingHours> getWorkingHours();
   WorkingHours getWorkingHours(long id);
   WorkingHours save(WorkingHours workingHours);
   void delete(WorkingHours workingHours);
}
