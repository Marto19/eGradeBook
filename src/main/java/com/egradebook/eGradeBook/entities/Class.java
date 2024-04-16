package com.egradebook.eGradeBook.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "classes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Class
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private School schoolId;

    @ManyToOne
    @JoinColumn(name = "class_number")
    private ClassGradeNumber classGradeNumber;

    @ManyToOne
    @JoinColumn(name = "class_letter")
    private ClassLetterCatalog classLetterCatalog;

    @OneToMany
    private Set<Student> students;

    private LocalDate graduationDate;

}
