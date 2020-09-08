package com.udacity.jdnd.course3.critter.schedule.repository;

import com.udacity.jdnd.course3.critter.schedule.entity.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
    List<Schedule> findSchedulesByPetsIdIn(Collection<Long> petsIds);
    List<Schedule> findScheduleByEmployeesIdIn(Collection<Long> employeesIds);
    List<Schedule> findSchedulesByPetsOwnerId(Long id);
}
