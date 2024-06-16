package com.egradebook.eGradeBook.repositories;

import com.egradebook.eGradeBook.entities.DayOfWeekCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayOfWeekRepository extends JpaRepository<DayOfWeekCatalog, Long> {

}