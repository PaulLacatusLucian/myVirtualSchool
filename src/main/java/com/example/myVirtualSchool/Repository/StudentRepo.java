package com.example.myVirtualSchool.Repository;

import com.example.myVirtualSchool.Domain.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepo extends CrudRepository<Student, Long> {
}
