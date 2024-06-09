package com.egradebook.eGradeBook.repositories;

import com.egradebook.eGradeBook.DTOs.teacher.TeacherDTO;
import com.egradebook.eGradeBook.entities.Teacher;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {


    @Query("SELECT new com.egradebook.eGradeBook.DTOs.teacher.TeacherDTO(" +
            "t.id, t.firstName, t.lastName, t.phoneNumber)" +
            "FROM Teacher t")
    List<TeacherDTO> getTeachersDTO();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO teachers (users_user_id, school_id) " +
            "VALUES (:#{#teacher.id}, :#{#teacher.school.id})",
            nativeQuery = true)
    void insertTeacher(Teacher teacher);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_has_roles (user_id, role_id) VALUES (:userId, :roleId)", nativeQuery = true)
    void insertUserRole(Long userId, Long roleId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO teachers_qualifications (users_user_id, qualifications_id) VALUES (:userId, :qualificationId)", nativeQuery = true)
    void insertTeacherQualification(Long userId, Long qualificationId);

}
