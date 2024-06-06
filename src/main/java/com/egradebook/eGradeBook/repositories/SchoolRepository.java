package com.egradebook.eGradeBook.repositories;

import com.egradebook.eGradeBook.DTOs.school.SchoolDTO;
import com.egradebook.eGradeBook.entities.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {

    @Query("SELECT new com.egradebook.eGradeBook.DTOs.school.SchoolDTO(" +
            "s.id, s.name, s.address) " +
            "FROM School s")
    List<SchoolDTO> getAllSchools();
}
