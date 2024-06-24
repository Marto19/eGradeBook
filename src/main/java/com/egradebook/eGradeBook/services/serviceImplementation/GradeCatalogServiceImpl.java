package com.egradebook.eGradeBook.services.serviceImplementation;

import com.egradebook.eGradeBook.entities.GradeCatalog;
import com.egradebook.eGradeBook.exceptions.GradeCatalogNotFoundException;
import com.egradebook.eGradeBook.repositories.GradeCatalogRepository;
import com.egradebook.eGradeBook.services.GradeCatalogService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GradeCatalogServiceImpl implements GradeCatalogService {

    @Autowired
    private GradeCatalogRepository gradeCatalogRepository;

    @Override
    public List<GradeCatalog> getAll() {
        return gradeCatalogRepository.findAll();
    }

    @Override
    public void save(GradeCatalog gradeCatalog) throws GradeCatalogNotFoundException {

        if (gradeCatalog != null) {
            gradeCatalogRepository.save(gradeCatalog);
        } else {
            throw new GradeCatalogNotFoundException("Grade with ID: " + gradeCatalog.getId() + " not found");
        }

    }

    @Override
    public void update(Long gradeId, Double gradeSign) throws GradeCatalogNotFoundException {
        GradeCatalog gradeCatalog = gradeCatalogRepository.findById(gradeId)
                .orElseThrow(() -> new GradeCatalogNotFoundException("Grade with ID: " + gradeId + " not found"));

        gradeCatalog.setGradeNumber(gradeSign);

        gradeCatalogRepository.save(gradeCatalog);
    }

    @Override
    public void save(Double gradeSign) {
        GradeCatalog gradeCatalog = new GradeCatalog();
        gradeCatalog.setGradeNumber(gradeSign);
        gradeCatalogRepository.save(gradeCatalog);
    }

    @Override
    public GradeCatalog getById(Long gradeId) throws GradeCatalogNotFoundException {

        return gradeCatalogRepository.findById(gradeId)
                .orElseThrow(() -> new GradeCatalogNotFoundException("Grade with ID: " + gradeId + " not found"));
    }

    @Override
    public void delete(Long gradeId) throws GradeCatalogNotFoundException {

        GradeCatalog gradeCatalog = gradeCatalogRepository.findById(gradeId)
                .orElseThrow(() -> new GradeCatalogNotFoundException("Grade with ID: " + gradeId + " not found"));

        gradeCatalogRepository.delete(gradeCatalog);
    }

}
