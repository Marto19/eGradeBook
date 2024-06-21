package com.egradebook.eGradeBook.repositories;

import com.egradebook.eGradeBook.DTOs.student.StudentDTO;
import com.egradebook.eGradeBook.entities.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>
{
//    @Query("SELECT new com.egradebook.eGradeBook.DTOs.student.StudentDTO(" +
//            "s.id, s.firstName, s.lastName, s.address, s.email, " +
//            "(SELECT new com.egradebook.eGradeBook.DTOs.GradeDTO(g.id, g.grade.id, g.subjectId.id, g.studentId.id, g.teacherId.id) FROM Grade g WHERE g.studentId.id = s.id), " +
//            "(SELECT new com.egradebook.eGradeBook.DTOs.AbsenceDTO(a.id, a.studentId.id, a.subjectId.id, a.absenceDate) FROM Absence a WHERE a.studentId.id = s.id)) " +
//            "FROM Student s")
//    List<StudentDTO> getStudentsDTO();

    @Query("SELECT s FROM Student s " +
            "LEFT JOIN FETCH s.gradeSet g " +
            "LEFT JOIN FETCH s.absenceSet a")
    List<Student> findAllWithGradesAndAbsences();


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO students (id, classid_id, parent_id_id, school_id_id) " +
            "VALUES (:id, :classId, :parentId, :schoolId)",
            nativeQuery = true)
    void insertStudent(@Param("id") Long id,
                       @Param("classId") Long classId,
                       @Param("parentId") Long parentId,
                       @Param("schoolId") Long schoolId);

    boolean existsByEmail(String email);
}
