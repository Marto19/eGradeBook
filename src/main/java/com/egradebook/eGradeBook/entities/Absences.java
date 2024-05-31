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

    @ManyToOne(optional = false)
    private Student studentId;

    @ManyToOne(optional = false)
    private Subject subjectId;

    @NotNull
    private LocalDate absenceDate;
}
