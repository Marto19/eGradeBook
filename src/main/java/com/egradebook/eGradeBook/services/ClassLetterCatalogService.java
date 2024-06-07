package com.egradebook.eGradeBook.services;

import com.egradebook.eGradeBook.entities.ClassLetterCatalog;
import com.egradebook.eGradeBook.repositories.ClassLetterCatalogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassLetterCatalogService {

    private final ClassLetterCatalogRepository classLetterCatalogRepository;

    public ClassLetterCatalogService(ClassLetterCatalogRepository classLetterCatalogRepository) {
        this.classLetterCatalogRepository = classLetterCatalogRepository;
    }

    public List<ClassLetterCatalog> getAllClassGradeNumberCatalog() {
        return classLetterCatalogRepository.findAll();
    }

    public ClassLetterCatalog getClassLetterById(Long letterId) {
        return classLetterCatalogRepository.findById(letterId).orElse(null);
    }
}
