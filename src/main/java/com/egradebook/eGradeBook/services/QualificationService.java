package com.egradebook.eGradeBook.services;

import com.egradebook.eGradeBook.entities.Qualification;
import com.egradebook.eGradeBook.exceptions.QualificationNotFoundException;

import java.util.List;

public interface QualificationService {

    List<Qualification> getQualifications();

    Qualification getQualificationById(Long id) throws QualificationNotFoundException;
}
