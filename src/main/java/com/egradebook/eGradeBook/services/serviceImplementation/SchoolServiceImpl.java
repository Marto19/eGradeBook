package com.egradebook.eGradeBook.services.serviceImplementation;

import com.egradebook.eGradeBook.DTOs.school.SchoolDTO;
import com.egradebook.eGradeBook.entities.School;
import com.egradebook.eGradeBook.repositories.SchoolClassesRepository;
import com.egradebook.eGradeBook.repositories.SchoolRepository;
import com.egradebook.eGradeBook.services.SchoolService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;
    private final SchoolClassesRepository schoolClassesRepository;

    public SchoolServiceImpl(SchoolRepository schoolRepository, SchoolClassesRepository schoolClassesRepository) {
        this.schoolRepository = schoolRepository;
        this.schoolClassesRepository = schoolClassesRepository;
    }

    public List<SchoolDTO> getAllSchools() {
        return schoolRepository.getAllSchoolsDto();
    }

    public School getSchoolById(Long id) {
        return schoolRepository.findById(id).orElse(null);
    }

    public void delete(School school) {
        schoolRepository.delete(school);
    }


    @Override
    public List<SchoolDTO> getAllSchoolsDto() {
        return schoolRepository.getAllSchoolsDto();
    }


}
