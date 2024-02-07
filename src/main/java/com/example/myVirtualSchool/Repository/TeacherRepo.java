package com.example.myVirtualSchool.Repository;

import com.example.myVirtualSchool.Domain.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepo  extends CrudRepository<Teacher, Long> {
}
