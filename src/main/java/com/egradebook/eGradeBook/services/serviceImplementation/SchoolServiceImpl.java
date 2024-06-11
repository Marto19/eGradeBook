package com.egradebook.eGradeBook.services.serviceImplementation;

import com.egradebook.eGradeBook.DTOs.school.SchoolDTO;
import com.egradebook.eGradeBook.entities.Principal;
import com.egradebook.eGradeBook.entities.School;
import com.egradebook.eGradeBook.exceptions.PrincipalNotFoundException;
import com.egradebook.eGradeBook.exceptions.SchoolNotFoundException;
import com.egradebook.eGradeBook.repositories.PrincipalRepository;
import com.egradebook.eGradeBook.repositories.SchoolClassesRepository;
import com.egradebook.eGradeBook.repositories.SchoolRepository;
import com.egradebook.eGradeBook.services.SchoolService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;
    private final SchoolClassesRepository schoolClassesRepository;
    private final PrincipalRepository principalRepository;

    public SchoolServiceImpl(SchoolRepository schoolRepository, SchoolClassesRepository schoolClassesRepository, PrincipalRepository principalRepository) {
        this.schoolRepository = schoolRepository;
        this.schoolClassesRepository = schoolClassesRepository;
        this.principalRepository = principalRepository;
    }

    public List<SchoolDTO> getAllSchools() {
        return schoolRepository.getAllSchoolsDto();
    }

    public School getSchoolById(Long id) {
        return schoolRepository.findById(id).orElse(null);
    }

//    public void delete(School school) {
//        schoolRepository.delete(school);
//    }

    @Override
    public School findSchoolById(Long schoolId) throws SchoolNotFoundException {
        return schoolRepository.findById(schoolId)
                .orElseThrow(() -> new SchoolNotFoundException("School with ID: " + schoolId + " not found"));
    }

    @Override
    public void saveSchool(Long schoolId, String schoolName, String schoolAddress, Long principalId) throws PrincipalNotFoundException, SchoolNotFoundException {

        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new SchoolNotFoundException("School with ID:" + schoolId + " not found"));

        Principal principal = principalRepository.findById(principalId).
                orElseThrow(() -> new PrincipalNotFoundException("Principal with ID:" + principalId + "not found!"));

        school.setName(schoolName);
        school.setAddress(schoolAddress);
        school.setPrincipal(principal);

        schoolRepository.save(school);
    }

    @Override
    public void deleteSchool(Long schoolId) throws SchoolNotFoundException {

        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new SchoolNotFoundException("School with ID:" + schoolId + "not found!"));

        schoolRepository.delete(school);
    }

    @Override
    public List<SchoolDTO> getAllSchoolsDto() {
        return schoolRepository.getAllSchoolsDto();
    }

    @Override
    public void createSchool(String schoolName, String schoolAddress, Long principalId) throws PrincipalNotFoundException {

        Principal principal = principalRepository.findById(principalId)
                .orElseThrow(() -> new PrincipalNotFoundException("Principal with ID:" + principalId + "not found!"));

        School school = new School();
        school.setName(schoolName);
        school.setAddress(schoolAddress);
        school.setPrincipal(principal);

        schoolRepository.save(school);
    }
}
