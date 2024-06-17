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
public class Absence
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @NotNull
    private Student student;

    @ManyToOne
    @NotNull
    private Subject subject;

    @NotNull
    private LocalDate absenceDate;
}
