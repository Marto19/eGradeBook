package com.egradebook.eGradeBook.services.serviceImplementation;

import com.egradebook.eGradeBook.entities.ClassGradeNumberCatalog;
import com.egradebook.eGradeBook.exceptions.ClassGradeNumberCatalogNotFoundException;
import com.egradebook.eGradeBook.repositories.ClassGradeNumberCatalogRepository;
import com.egradebook.eGradeBook.services.ClassGradeNumberCatalogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor

@Service
public class ClassGradeNumberCatalogServiceImpl implements ClassGradeNumberCatalogService {


    private final ClassGradeNumberCatalogRepository classGradeNumberCatalogRepository;

    @Override
    public List<ClassGradeNumberCatalog> getAllClassGradeNumberCatalog() {
        return classGradeNumberCatalogRepository.findAll();
    }

    @Override
    public ClassGradeNumberCatalog getClassGradeNumberById(Long gradeId) throws ClassGradeNumberCatalogNotFoundException {

        return classGradeNumberCatalogRepository.findById(gradeId)
                .orElseThrow(() -> new ClassGradeNumberCatalogNotFoundException("Class Grade Number with ID: " + gradeId + " not found"));
    }

    @Override
    public void create(int cgNumber) {
        ClassGradeNumberCatalog classGradeNumberCatalog = new ClassGradeNumberCatalog();
        classGradeNumberCatalog.setCgNumber(cgNumber);
        classGradeNumberCatalogRepository.save(classGradeNumberCatalog);
    }

    @Override
    public void update(int cgId, int cgNumber) throws ClassGradeNumberCatalogNotFoundException {
        ClassGradeNumberCatalog classGradeNumberCatalog = classGradeNumberCatalogRepository.findById((long) cgId)
                .orElseThrow(() -> new ClassGradeNumberCatalogNotFoundException("Class Grade Number with ID: " + cgId + " not found"));

        classGradeNumberCatalog.setCgNumber(cgNumber);
        classGradeNumberCatalogRepository.save(classGradeNumberCatalog);
    }

    @Override
    public void delete(int cgId) throws ClassGradeNumberCatalogNotFoundException {
        ClassGradeNumberCatalog classGradeNumberCatalog = classGradeNumberCatalogRepository.findById((long) cgId)
                .orElseThrow(() -> new ClassGradeNumberCatalogNotFoundException("Class Grade Number with ID: " + cgId + " not found"));

        classGradeNumberCatalogRepository.delete(classGradeNumberCatalog);
    }
}
