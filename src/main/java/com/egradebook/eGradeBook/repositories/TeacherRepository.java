package com.egradebook.eGradeBook.repositories;

import com.egradebook.eGradeBook.DTOs.teacher.TeacherDTO;
import com.egradebook.eGradeBook.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {


    @Query("SELECT new com.egradebook.eGradeBook.DTOs.teacher.TeacherDTO(" +
            "t.id, t.firstName, t.lastName, t.phoneNumber)" +
            "FROM Teacher t")
    List<TeacherDTO> getTeachersDTO();

}
