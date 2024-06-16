package com.egradebook.eGradeBook.repositories;

import com.egradebook.eGradeBook.entities.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {

}
