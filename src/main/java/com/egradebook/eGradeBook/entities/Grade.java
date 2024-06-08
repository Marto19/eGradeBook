package com.egradebook.eGradeBook.entities;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "grades")
public class Grade
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private GradeCatalog grade;

    @ManyToOne
    private Subject subjectId;

    @ManyToOne
    private Student studentId;

    @ManyToOne
    private Teacher teacherId;
}
