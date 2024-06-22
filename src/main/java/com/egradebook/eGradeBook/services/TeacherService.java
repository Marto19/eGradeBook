package com.egradebook.eGradeBook.services;

import com.egradebook.eGradeBook.DTOs.teacher.TeacherDTO;
import com.egradebook.eGradeBook.entities.Teacher;
import com.egradebook.eGradeBook.exceptions.TeacherNotFoundException;

import java.util.Collection;
import java.util.List;

public interface TeacherService {

    Teacher updateTeacher(Teacher teacher);
    Teacher findById(Long id) throws TeacherNotFoundException;

    TeacherDTO findTeacherDTOById(Long id);

    List<TeacherDTO> getAllTeachersDto();

    void createTeacher(Long userId, Long schoolId, Collection<Long> qualificationIds);
    void deleteTeacher(Long userId) throws TeacherNotFoundException;
}
