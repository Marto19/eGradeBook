package com.egradebook.eGradeBook.DTOs.absence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AbsenceSummaryDTO {

    private Long id;
    private Long studentId;
    private String studentFirstname;
    private String studentLastname;
    private String subjectName;
    private LocalDate absenceDate;
}
