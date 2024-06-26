package com.egradebook.eGradeBook.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

/*
TODO SLOJI @NOTNULL NAVSQKUDE
 */


@Entity
@Table(name = "absences")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Absences
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Student studentId;

    @ManyToOne
    private Subject subjectId;

    @NotNull
    private LocalDate absenceDate;
}
