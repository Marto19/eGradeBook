package com.egradebook.eGradeBook.repositories;

import com.egradebook.eGradeBook.DTOs.absence.AbsenceSummaryDTO;
import com.egradebook.eGradeBook.entities.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long>
{
    Optional<Absence> findById(Long id);

   // Optional<Absence> findAllByStudentId(Long studentId);

    List<Absence> findAll();

    List<Absence> findAllByAbsenceDate(LocalDate date);

    @Query("SELECT new com.egradebook.eGradeBook.DTOs.absence.AbsenceSummaryDTO(a.id, a.studentId.id, a.studentId.firstName, a.studentId.lastName, a.subjectId.name, a.absenceDate) " +
            "FROM Absence a " +
            "WHERE a.studentId.id = :studentId")
    List<AbsenceSummaryDTO> getStudentAbsenceDTOsById (@Param("studentId") Long studentId);
}
