package com.egradebook.eGradeBook.services;

import com.egradebook.eGradeBook.entities.Grade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GradeService {

    List<Grade> findByStudentId(Long studentId);

}
