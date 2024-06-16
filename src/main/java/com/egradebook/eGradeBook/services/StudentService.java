package com.egradebook.eGradeBook.services;

import com.egradebook.eGradeBook.DTOs.classes.SchoolClassDTO;
import com.egradebook.eGradeBook.DTOs.student.StudentDTO;
import com.egradebook.eGradeBook.entities.Student;
import com.egradebook.eGradeBook.exceptions.StudentNotFoundException;

import java.util.List;

public interface StudentService {

    Student getStudentById(Long id) throws StudentNotFoundException;
    Student createStudent(Student student);
    Student updateStudent(Student student) throws StudentNotFoundException;
    void deleteStudent(Long id) throws StudentNotFoundException;
    List<Student> listAllStudents();

    StudentDTO getStudentDtoById(Long id) throws StudentNotFoundException;

    List<StudentDTO> getAllStudentsDto();
    void saveOrUpdate(Student student);

    List<SchoolClassDTO> getAllClassesDto();
}