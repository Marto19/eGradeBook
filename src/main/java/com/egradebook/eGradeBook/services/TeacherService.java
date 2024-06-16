package com.egradebook.eGradeBook.services;

import com.egradebook.eGradeBook.DTOs.teacher.TeacherDTO;
import com.egradebook.eGradeBook.entities.Teacher;
import com.egradebook.eGradeBook.exceptions.QualificationNotFoundException;
import com.egradebook.eGradeBook.exceptions.SchoolNotFoundException;
import com.egradebook.eGradeBook.exceptions.TeacherNotFoundException;
import com.egradebook.eGradeBook.exceptions.UserNotFoundException;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.Set;

public interface TeacherService {

    Teacher updateTeacher(Teacher teacher);

    List<TeacherDTO> getAllTeachersDto();

    void createTeacher(TeacherDTO teacherDTO);
    void deleteTeacher(Long userId) throws TeacherNotFoundException;
}
