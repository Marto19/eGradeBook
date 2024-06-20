package com.egradebook.eGradeBook.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AbsenceDTO {
    private long id;
    private long studentId;
    private long subjectId;
    private LocalDate absenceDate;
}
