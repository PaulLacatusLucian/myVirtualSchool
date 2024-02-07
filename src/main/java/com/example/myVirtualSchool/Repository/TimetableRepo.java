package com.example.myVirtualSchool.Repository;

import com.example.myVirtualSchool.Domain.Timetable;
import org.springframework.data.repository.CrudRepository;

public interface TimetableRepo  extends CrudRepository<Timetable, Long> {
}
