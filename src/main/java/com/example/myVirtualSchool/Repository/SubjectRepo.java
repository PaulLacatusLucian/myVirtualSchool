package com.example.myVirtualSchool.Repository;

import com.example.myVirtualSchool.Domain.Subject;
import org.springframework.data.repository.CrudRepository;

public interface SubjectRepo extends CrudRepository<Subject, Long> {
}
