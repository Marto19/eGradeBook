package com.egradebook.eGradeBook.services;

import com.egradebook.eGradeBook.entities.Subject;
import com.egradebook.eGradeBook.exceptions.QualificationNotFoundException;
import com.egradebook.eGradeBook.exceptions.SubjectNotFoundException;

import java.util.List;

public interface SubjectService {

    Subject getSubjectById(Long id) throws SubjectNotFoundException;
    Subject createSubject(Subject subject);
    Subject updateSubject(Subject subject);
    void deleteSubject(Long id) throws SubjectNotFoundException;
    List<Subject> listAllSubjects();

    Subject createSubject(String name, Long qualificationId, String description) throws QualificationNotFoundException;
    Subject updateSubject(Long subjectId, String subjectName, Long qualificationId, String subjectDescription) throws SubjectNotFoundException, QualificationNotFoundException;


}
