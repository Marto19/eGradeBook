package com.egradebook.eGradeBook.DTOs.absence;

import com.egradebook.eGradeBook.entities.Student;
import com.egradebook.eGradeBook.entities.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AbsenceDTO
{
    private long id;

    private Student studentId;

    private Subject subjectId;

    private LocalDate absenceDate;
}
