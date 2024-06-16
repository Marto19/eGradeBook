package com.egradebook.eGradeBook.services.serviceImplementation;

import com.egradebook.eGradeBook.entities.Qualification;
import com.egradebook.eGradeBook.entities.Subject;
import com.egradebook.eGradeBook.exceptions.QualificationNotFoundException;
import com.egradebook.eGradeBook.exceptions.SubjectNotFoundException;
import com.egradebook.eGradeBook.repositories.SubjectRepository;
import com.egradebook.eGradeBook.services.QualificationService;
import com.egradebook.eGradeBook.services.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final QualificationService qualificationService;

    @Override
    public Subject getSubjectById(Long id) throws SubjectNotFoundException {

        return subjectRepository.findById(id)
                .orElseThrow(() -> new SubjectNotFoundException("Subject with ID: " + id + "not found!"));
    }

    @Override
    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Subject updateSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public void deleteSubject(Long id) throws SubjectNotFoundException {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new SubjectNotFoundException("Subject with ID: " + id + "not found!"));

        subjectRepository.delete(subject);
    }

    @Override
    public List<Subject> listAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject createSubject(String name, Long qualificationId, String description) throws QualificationNotFoundException {

        Subject subject = new Subject();
        Qualification requiredQualification = qualificationService.getQualificationById(qualificationId);

        subject.setName(name);
        subject.setQualification(requiredQualification);
        subject.setDescription(description);

        return subjectRepository.save(subject);
    }

    @Override
    public Subject updateSubject(Long subjectId, String subjectName, Long qualificationId, String subjectDescription) throws SubjectNotFoundException, QualificationNotFoundException {

        Qualification qualification = qualificationService.getQualificationById(qualificationId);

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new SubjectNotFoundException("Subject with ID: " + subjectId + "not found!"));

        subject.setName(subjectName);
        subject.setQualification(qualification);
        subject.setDescription(subjectDescription);

        return subjectRepository.save(subject);
    }
}
