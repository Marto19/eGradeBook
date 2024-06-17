package com.egradebook.eGradeBook.repositories;

import com.egradebook.eGradeBook.entities.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long>
{
    Optional<Absence> findById(Long id);

    Optional<Absence> findByStudentId(Long studentId);

    List<Absence> findAll();

    List<Absence> findAllByAbsenceDate(LocalDate date);
}
