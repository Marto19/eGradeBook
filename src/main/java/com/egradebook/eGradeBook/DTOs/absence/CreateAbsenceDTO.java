package com.egradebook.eGradeBook.DTOs.absence;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateAbsenceDTO
{
    @NotNull
    private Long studentId;

    @NotNull
    private Long subjectId;

    @NotNull
    private LocalDate absenceDate;
}
