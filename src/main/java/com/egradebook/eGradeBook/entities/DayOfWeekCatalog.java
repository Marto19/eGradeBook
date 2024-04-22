package com.egradebook.eGradeBook.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "day_of_week_catalog")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DayOfWeekCatalog
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String day;

    @OneToMany(mappedBy = "dayOfWeek")
    private Set<Curriculum> curriculumSet;
}
