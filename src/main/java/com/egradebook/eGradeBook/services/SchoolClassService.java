package com.egradebook.eGradeBook.services;

import com.egradebook.eGradeBook.entities.Class;
import com.egradebook.eGradeBook.repositories.SchoolClassesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolClassService {

    private final SchoolClassesRepository schoolClassesRepository;

    public SchoolClassService(SchoolClassesRepository schoolClassesRepository) {
        this.schoolClassesRepository = schoolClassesRepository;
    }

    public List<Class> getAllClasses() {
        return schoolClassesRepository.findAll();
    }

    public void saveClass(Class newClass) {
        schoolClassesRepository.save(newClass);
    }

    public Class getClassById(Long classId) {
        return schoolClassesRepository.findById(classId).orElse(null);
    }
}
