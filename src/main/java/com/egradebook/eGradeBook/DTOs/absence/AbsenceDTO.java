package com.egradebook.eGradeBook.DTOs.absence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AbsenceDTO {
    private long id;
    private Long studentId;
    private String studentName; // Add this field
    private Long subjectId;
    private String subjectName; // Add this field
    private LocalDate absenceDate;
}
