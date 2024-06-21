package com.egradebook.eGradeBook.DTOs.subject;

import com.egradebook.eGradeBook.entities.Absence;
import com.egradebook.eGradeBook.entities.Curriculum;
import com.egradebook.eGradeBook.entities.Grade;
import com.egradebook.eGradeBook.entities.Qualification;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO
{
    private long id;

    @NotBlank(message = "Subject name cannot be blank")
    private String name;

    private String description;

    private Set<Curriculum> curriculumSet;

    private Set<Grade> gradeSet;

    private Set<Absence> absenceSet;

    @NotNull
    private Qualification qualification;
}
