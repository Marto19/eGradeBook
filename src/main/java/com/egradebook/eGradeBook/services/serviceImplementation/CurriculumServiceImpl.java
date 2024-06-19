package com.egradebook.eGradeBook.services.serviceImplementation;

import com.egradebook.eGradeBook.entities.Class;
import com.egradebook.eGradeBook.entities.Curriculum;
import com.egradebook.eGradeBook.entities.DayOfWeekCatalog;
import com.egradebook.eGradeBook.entities.Subject;
import com.egradebook.eGradeBook.entities.Teacher;
import com.egradebook.eGradeBook.exceptions.DayOfWeekNotFoundException;
import com.egradebook.eGradeBook.exceptions.SubjectNotFoundException;
import com.egradebook.eGradeBook.exceptions.TeacherNotFoundException;
import com.egradebook.eGradeBook.repositories.CurriculumRepository;
import com.egradebook.eGradeBook.repositories.TeacherRepository;
import com.egradebook.eGradeBook.services.ClassService;
import com.egradebook.eGradeBook.services.CurriculumService;
import com.egradebook.eGradeBook.services.DayOfWeekService;
import com.egradebook.eGradeBook.services.SubjectService;
import com.egradebook.eGradeBook.services.TeacherService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class CurriculumServiceImpl implements CurriculumService {

    private final CurriculumRepository curriculumRepository;
    private final ClassService classService;
    private final DayOfWeekService dayOfWeekService;
    private final SubjectService subjectService;
    private final TeacherRepository teacherRepository;

    @Override
    public Curriculum findCurriculumById(Long id) {
        return curriculumRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Curriculum createCurriculum(Curriculum curriculum) {
        return curriculumRepository.save(curriculum);
    }

    @Override
    public Curriculum updateCurriculum(Curriculum curriculum) {
        return curriculumRepository.save(curriculum);
    }

    @Override
    public void deleteCurriculum(Long id) throws EntityNotFoundException {
        Curriculum curriculum = curriculumRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        curriculumRepository.delete(curriculum);
    }

    @Override
    public List<Curriculum> listCurriculum() {
        return curriculumRepository.findAll();
    }

    @Override
    public Curriculum createCurriculum(Long classId, Long dayOfWeekId, Long subjectId, Long teacherId, LocalDate period)
            throws DayOfWeekNotFoundException, SubjectNotFoundException {

        Class currClass = classService.getClassById(classId);
        DayOfWeekCatalog dayOfWeekCatalog = dayOfWeekService.findDayOfWeekById(dayOfWeekId);
        Subject subject = subjectService.getSubjectById(subjectId);
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow();

        Curriculum curriculum = new Curriculum();
        curriculum.setClassId(currClass);
        curriculum.setDayOfWeek(dayOfWeekCatalog);
        curriculum.setSubjectId(subject);
        curriculum.setTeacherId(teacher);
        curriculum.setPeriodTime(period);

        return curriculumRepository.save(curriculum);
    }

    @Override
    public Curriculum updateCurriculum(Long curriculumId, Long classId, Long dayOfWeekId, Long subjectId, Long teacherId, LocalDate period) throws SubjectNotFoundException, DayOfWeekNotFoundException {

        Class currClass = classService.getClassById(classId);
        DayOfWeekCatalog dayOfWeekCatalog = dayOfWeekService.findDayOfWeekById(dayOfWeekId);
        Subject subject = subjectService.getSubjectById(subjectId);
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow();
        Curriculum curriculum = findCurriculumById(curriculumId);

        curriculum.setClassId(currClass);
        curriculum.setDayOfWeek(dayOfWeekCatalog);
        curriculum.setSubjectId(subject);
        curriculum.setTeacherId(teacher);
        curriculum.setPeriodTime(period);

        return curriculumRepository.save(curriculum);
    }
}
