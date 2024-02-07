package com.example.myVirtualSchool.Repository;

import com.example.myVirtualSchool.Domain.Grade;
import org.springframework.data.repository.CrudRepository;

public interface GradeRepo  extends CrudRepository<Grade, Long> {
}
