package com.egradebook.eGradeBook.services.serviceImplementation;

import com.egradebook.eGradeBook.entities.Qualification;
import com.egradebook.eGradeBook.exceptions.QualificationNotFoundException;
import com.egradebook.eGradeBook.repositories.QualificationsRepository;
import com.egradebook.eGradeBook.services.QualificationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QualificationServiceImpl implements QualificationService {

    private final QualificationsRepository qualificationsRepository;

    public QualificationServiceImpl(QualificationsRepository qualificationsRepository) {
        this.qualificationsRepository = qualificationsRepository;
    }

    @Override
    public List<Qualification> getQualifications() {
        return qualificationsRepository.findAll();
    }

    @Override
    public Qualification getQualificationById(Long id) throws QualificationNotFoundException {
        return qualificationsRepository.findById(id)
                .orElseThrow(() -> new QualificationNotFoundException("Qualification with ID: " + id + "not found!"));
    }

}
