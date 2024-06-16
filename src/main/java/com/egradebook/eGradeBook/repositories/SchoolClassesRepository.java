package com.egradebook.eGradeBook.repositories;

import com.egradebook.eGradeBook.DTOs.classes.SchoolClassDTO;
import com.egradebook.eGradeBook.entities.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolClassesRepository extends JpaRepository<Class, Long> {

    @Query("SELECT new com.egradebook.eGradeBook.DTOs.classes.SchoolClassDTO(" +
            "c.id, c.schoolId.name, c.classLetterCatalog.cLetter, c.classGradeNumber.cgNumber) " +
            "FROM Class c")
    List<SchoolClassDTO> findAllClassesDTO();


}
