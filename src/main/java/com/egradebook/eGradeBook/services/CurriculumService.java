package com.egradebook.eGradeBook.services;


import com.egradebook.eGradeBook.entities.Curriculum;
import com.egradebook.eGradeBook.exceptions.DayOfWeekNotFoundException;
import com.egradebook.eGradeBook.exceptions.SubjectNotFoundException;
import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface CurriculumService {

    Curriculum findCurriculumById(Long id);
    Curriculum createCurriculum(Curriculum curriculum);
    Curriculum updateCurriculum(Curriculum curriculum);
    void deleteCurriculum(Long id) throws EntityNotFoundException;
    List<Curriculum> listCurriculum();

    Curriculum createCurriculum(Long classId, Long dayOfWeekId,
                                Long subjectId, Long teacherId, LocalDate dateTime) throws DayOfWeekNotFoundException, SubjectNotFoundException;

    Curriculum updateCurriculum(Long curriculumId, Long classId, Long dayOfWeekId,
                                Long subjectId, Long teacherId, LocalDate dateTime) throws SubjectNotFoundException, DayOfWeekNotFoundException;

}
