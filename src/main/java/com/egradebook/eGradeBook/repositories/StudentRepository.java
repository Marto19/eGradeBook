package com.egradebook.eGradeBook.repositories;

import com.egradebook.eGradeBook.DTOs.student.StudentDTO;
import com.egradebook.eGradeBook.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT new com.egradebook.eGradeBook.DTOs.student.StudentDTO(" +
            "s.id, s.firstName, s.lastName, s.address, s.email, " +
            "(SELECT new com.egradebook.eGradeBook.DTOs.GradeDTO(g.id, g.grade.id, g.subjectId.id, g.studentId.id, g.teacherId.id) FROM Grade g WHERE g.studentId = s), " +
            "(SELECT new com.egradebook.eGradeBook.DTOs.AbsenceDTO(a.id, a.studentId.id, a.subjectId.id, a.absenceDate) FROM Absences a WHERE a.studentId = s)) " +
            "FROM Student s")
    List<StudentDTO> getStudentsDTO();




    boolean existsByEmail(String email);
}
