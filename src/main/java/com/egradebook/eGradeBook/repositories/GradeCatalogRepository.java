package com.egradebook.eGradeBook.repositories;

import com.egradebook.eGradeBook.entities.GradeCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeCatalogRepository extends JpaRepository<GradeCatalog, Long> {
}
