package com.egradebook.eGradeBook.services.serviceImplementation;

import com.egradebook.eGradeBook.DTOs.GradeDTO;
import com.egradebook.eGradeBook.entities.*;
import com.egradebook.eGradeBook.repositories.*;
import com.egradebook.eGradeBook.services.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final GradeCatalogRepository gradeCatalogRepository;
    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public List<Grade> findByStudentId(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    @Override
    public List<GradeDTO> getAllGrades() {
        return gradeRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public GradeDTO getGradeById(Long id) {
        Grade grade = gradeRepository.findById(Math.toIntExact(id)).orElseThrow(() -> new RuntimeException("Grade not found"));
        return convertToDTO(grade);
    }

    @Override
    public GradeDTO createGrade(GradeDTO gradeDTO) {
        Grade grade = new Grade();
        grade.setGrade(findGradeCatalogById(gradeDTO.getGradeCatalogId()));
        grade.setSubjectId(findSubjectById(gradeDTO.getSubjectId()));
        grade.setStudentId(findStudentById(gradeDTO.getStudentId()));
        grade.setTeacherId(findTeacherById(gradeDTO.getTeacherId()));
        grade = gradeRepository.save(grade);
        return convertToDTO(grade);
    }

    @Override
    public GradeDTO updateGrade(Long id, GradeDTO gradeDTO) {
        Grade grade = gradeRepository.findById(Math.toIntExact(id)).orElseThrow(() -> new RuntimeException("Grade not found"));
        grade.setGrade(findGradeCatalogById(gradeDTO.getGradeCatalogId()));
        grade.setSubjectId(findSubjectById(gradeDTO.getSubjectId()));
        grade.setStudentId(findStudentById(gradeDTO.getStudentId()));
        grade.setTeacherId(findTeacherById(gradeDTO.getTeacherId()));
        grade = gradeRepository.save(grade);
        return convertToDTO(grade);
    }

    @Override
    public void deleteGrade(Long id) {
        gradeRepository.deleteById(Math.toIntExact(id));
    }

    @Override
    public List<GradeCatalog> getAllGradeCatalogs() {
        return gradeCatalogRepository.findAll();
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public List<Grade> getAllGradesWithDetails() {
        return gradeRepository.findAll();
    }

    private GradeDTO convertToDTO(Grade grade) {
        GradeDTO dto = new GradeDTO();
        dto.setId(grade.getId());
        dto.setGradeCatalogId(grade.getGrade().getId());
        dto.setSubjectId(grade.getSubjectId().getId());
        dto.setStudentId(grade.getStudentId().getId());
        dto.setTeacherId(grade.getTeacherId().getId());
        return dto;
    }

    private GradeCatalog findGradeCatalogById(Long id) {
        return gradeCatalogRepository.findById(id).orElseThrow(() -> new RuntimeException("Grade catalog not found"));
    }

    private Subject findSubjectById(Long id) {
        return subjectRepository.findById(id).orElseThrow(() -> new RuntimeException("Subject not found"));
    }

    private Student findStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    private Teacher findTeacherById(Long id) {
        return teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found"));
    }
}
