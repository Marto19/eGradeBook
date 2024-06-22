package com.egradebook.eGradeBook.repositories;

import com.egradebook.eGradeBook.DTOs.teacher.TeacherDTO;
import com.egradebook.eGradeBook.entities.Teacher;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {


    @Query("SELECT new com.egradebook.eGradeBook.DTOs.teacher.TeacherDTO(" +
            "t.id, t.firstName, t.lastName, t.phoneNumber)" +
            "FROM Teacher t WHERE t.enabled = true")
    List<TeacherDTO> getTeachersDTO();

    @Query("SELECT new com.egradebook.eGradeBook.DTOs.teacher.TeacherDTO(t.id, t.firstName, t.lastName, t.phoneNumber) FROM Teacher t WHERE t.id = :id ")
    TeacherDTO findTeacherDTOById(Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO teachers (users_user_id, school_id) VALUES (:userId, :schoolId)", nativeQuery = true)
    void insertTeacher(@Param("userId") Long userId, @Param("schoolId") Long schoolId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_has_roles (user_id, role_id) VALUES (:userId, :roleId)", nativeQuery = true)
    void insertUserRole(Long userId, Long roleId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO teachers_qualficiations (users_id, qualifications_id) VALUES (:userId, :qualificationId)", nativeQuery = true)
    void insertTeacherQualification(@Param("userId") Long userId, @Param("qualificationId") Long qualificationId);

}
