package com.egradebook.eGradeBook.services;

import com.egradebook.eGradeBook.entities.ClassGradeNumberCatalog;
import com.egradebook.eGradeBook.exceptions.ClassGradeNumberCatalogNotFoundException;
import com.egradebook.eGradeBook.repositories.ClassGradeNumberCatalogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClassGradeNumberCatalogService {

    List<ClassGradeNumberCatalog> getAllClassGradeNumberCatalog();
    ClassGradeNumberCatalog getClassGradeNumberById(Long gradeId) throws ClassGradeNumberCatalogNotFoundException;
    void create(int cgNumber);
    void update(int cgId, int cgNumber) throws ClassGradeNumberCatalogNotFoundException;
    void delete(int cgId) throws ClassGradeNumberCatalogNotFoundException;
}
