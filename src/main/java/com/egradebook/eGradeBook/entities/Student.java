package com.egradebook.eGradeBook.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Student extends User
{
    @ManyToOne
    @NotNull
    private School schoolId;

    @OneToOne
    @NotNull
    private Parent parentId;

    @ManyToOne()
    @NotNull
    private Class classID;

    @OneToMany(mappedBy = "studentId")
    private Set<Grade> gradeSet;

    @OneToMany(mappedBy = "studentId")
    private Set<Absences> absencesSet;
}
