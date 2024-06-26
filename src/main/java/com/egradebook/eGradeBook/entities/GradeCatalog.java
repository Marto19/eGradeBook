package com.egradebook.eGradeBook.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "grade_catalog")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GradeCatalog
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double gradeNumber;

    @OneToMany(mappedBy = "grade")
    private Set<Grade> gradeSet;
}
