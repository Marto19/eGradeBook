package com.egradebook.eGradeBook.repositories;

import com.egradebook.eGradeBook.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Integer> {


    @Query("SELECT g FROM Grade g WHERE g.studentId.id = :studentId")
    List<Grade> findByStudentId(Long studentId);


}
