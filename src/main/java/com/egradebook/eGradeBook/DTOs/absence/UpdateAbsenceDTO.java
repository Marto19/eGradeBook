package com.egradebook.eGradeBook.DTOs.absence;

import com.egradebook.eGradeBook.entities.Student;
import com.egradebook.eGradeBook.entities.Subject;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class UpdateAbsenceDTO
{
    @NotNull
    private long id;

    @NotNull
    private Student studentId;

    @NotNull
    private Subject subjectId;

    @NotNull
    private LocalDate absenceDate;
}
