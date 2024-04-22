package com.egradebook.eGradeBook.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "curriculum")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Curriculum
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Class classId;

    @ManyToOne
    private Subject subjectId;

    @ManyToOne
    private Teacher teacherId;

    private LocalDate periodTime;

    @ManyToOne
    private DayOfWeekCatalog dayOfWeek;
}
