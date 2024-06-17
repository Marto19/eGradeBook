package com.egradebook.eGradeBook.repositories;

import com.egradebook.eGradeBook.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long>
{

}
