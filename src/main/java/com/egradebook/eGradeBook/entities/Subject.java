package com.egradebook.eGradeBook.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "subjects")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subject
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Subject name cannot be blank")
    private String name;

    private String description;

    @OneToMany(mappedBy = "subjectId")
    private Set<Curriculum> curriculumSet;

    @OneToMany(mappedBy = "subjectId")
    private Set<Grade> gradeSet;

    @OneToMany(mappedBy = "subjectId")
    private Set<Absence> absenceSet;

    @ManyToOne
    @NotNull
    private Qualification qualification;
}
