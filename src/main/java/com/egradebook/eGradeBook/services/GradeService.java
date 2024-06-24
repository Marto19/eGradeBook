package com.egradebook.eGradeBook.services;

import com.egradebook.eGradeBook.DTOs.GradeDTO;
import com.egradebook.eGradeBook.entities.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GradeService {

    List<Grade> findByStudentId(Long studentId);
    List<GradeDTO> getAllGrades();
    GradeDTO getGradeById(Long id);
    GradeDTO createGrade(GradeDTO gradeDTO);
    GradeDTO updateGrade(Long id, GradeDTO gradeDTO);
    void deleteGrade(Long id);
    List<GradeCatalog> getAllGradeCatalogs();
    List<Subject> getAllSubjects();
    List<Student> getAllStudents();
    List<Teacher> getAllTeachers();
    public List<Grade> getAllGradesWithDetails();
}
