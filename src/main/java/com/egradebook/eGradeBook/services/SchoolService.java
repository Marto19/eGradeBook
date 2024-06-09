package com.egradebook.eGradeBook.services;

import com.egradebook.eGradeBook.DTOs.school.SchoolDTO;
import com.egradebook.eGradeBook.entities.School;
import com.egradebook.eGradeBook.exceptions.PrincipalNotFoundException;
import com.egradebook.eGradeBook.exceptions.SchoolNotFoundException;

import java.util.List;

public interface SchoolService {

    List<SchoolDTO> getAllSchoolsDto();

    void createSchool(String schoolName, String schoolAddress, Long principalId) throws PrincipalNotFoundException;

    void deleteSchool(Long schoolId) throws SchoolNotFoundException;

    void saveSchool(Long schoolId, String schoolName, String schoolAddress, Long principalId) throws PrincipalNotFoundException, SchoolNotFoundException;

    School findSchoolById(Long schoolId) throws SchoolNotFoundException;

}
