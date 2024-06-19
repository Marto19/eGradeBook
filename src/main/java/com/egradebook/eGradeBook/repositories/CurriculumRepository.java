package com.egradebook.eGradeBook.repositories;

import com.egradebook.eGradeBook.entities.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {
}
