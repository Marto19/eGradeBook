package com.egradebook.eGradeBook.repositories;

import com.egradebook.eGradeBook.entities.ClassGradeNumberCatalog;
import com.egradebook.eGradeBook.entities.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QualificationsRepository extends JpaRepository<Qualification, Long> {
}
