package com.egradebook.eGradeBook.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "absences")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Absence
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "student_id_id")
    @NotNull
    private Student studentId;

    @ManyToOne
    @JoinColumn(name = "subject_id_id")
    @NotNull
    private Subject subjectId;

    @NotNull
    private LocalDate absenceDate;
}
