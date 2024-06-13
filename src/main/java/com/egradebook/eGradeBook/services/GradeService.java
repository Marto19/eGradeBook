package com.egradebook.eGradeBook.services;

import com.egradebook.eGradeBook.entities.GradeCatalog;
import com.egradebook.eGradeBook.exceptions.GradeCatalogNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GradeService {

    GradeCatalog getById(Long id) throws GradeCatalogNotFoundException;
    List<GradeCatalog> getAll();
    void save(Double gradeSign);
    void save(GradeCatalog gradeCatalog) throws GradeCatalogNotFoundException;
    void update(Long gradeId, Double gradeSign) throws GradeCatalogNotFoundException;
    void delete(Long gradeId) throws GradeCatalogNotFoundException;
}
