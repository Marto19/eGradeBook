package com.egradebook.eGradeBook.repositories;

import com.egradebook.eGradeBook.entities.ClassGradeNumberCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassGradeNumberCatalogRepository extends JpaRepository<ClassGradeNumberCatalog, Long> {
}
