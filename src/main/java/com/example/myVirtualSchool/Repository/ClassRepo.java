package com.example.myVirtualSchool.Repository;

import com.example.myVirtualSchool.Domain.Class;
import org.springframework.data.repository.CrudRepository;

public interface ClassRepo extends CrudRepository<Class, Long> {
}
