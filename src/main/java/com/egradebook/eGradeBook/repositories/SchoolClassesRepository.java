package com.egradebook.eGradeBook.repositories;

import com.egradebook.eGradeBook.entities.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolClassesRepository extends JpaRepository<Class, Long> {

}
