package com.egradebook.eGradeBook.services;

import com.egradebook.eGradeBook.entities.ClassGradeNumberCatalog;
import com.egradebook.eGradeBook.repositories.ClassGradeNumberCatalogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassGradeNumberCatalogService {

    private final ClassGradeNumberCatalogRepository classGradeNumberCatalogRepository;

    public ClassGradeNumberCatalogService(ClassGradeNumberCatalogRepository classGradeNumberCatalogRepository) {
        this.classGradeNumberCatalogRepository = classGradeNumberCatalogRepository;
    }

    public List<ClassGradeNumberCatalog> getAllClassGradeNumberCatalog() {
        return classGradeNumberCatalogRepository.findAll();
    }

    public ClassGradeNumberCatalog getClassGradeNumberById(Long gradeId) {
        return classGradeNumberCatalogRepository.findById(gradeId).orElse(null);
    }
}
