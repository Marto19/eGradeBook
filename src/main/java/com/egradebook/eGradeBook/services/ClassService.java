package com.egradebook.eGradeBook.services;

import com.egradebook.eGradeBook.entities.Class;
import com.egradebook.eGradeBook.repositories.ClassRepository;
import org.springframework.stereotype.Service;

@Service
public class ClassService {

    private ClassRepository classRepository;

    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public Class getClassById(Long id) {
        return classRepository.findById(id).orElse(null);
    }

    public void delete(Class deleteClass) {
        classRepository.delete(deleteClass);
    }
}
