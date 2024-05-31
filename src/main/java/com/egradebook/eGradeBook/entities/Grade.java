package com.egradebook.eGradeBook.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "grades")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Grade
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false)
    private GradeCatalog grade;

    @ManyToOne(optional = false)
    private Subject subjectId;

    @ManyToOne(optional = false)
    private Student studentId;

    @ManyToOne(optional = false)
    private Teacher teacherId;
}
