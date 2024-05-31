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

    @ManyToOne(optional = false)
    private School schoolId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "class_number")
    private ClassGradeNumberCatalog classGradeNumber;

    @ManyToOne(optional = false)
    @JoinColumn(name = "class_letter")
    private ClassLetterCatalog classLetterCatalog;

    @OneToMany(mappedBy = "classID")
    private Set<Student> students;

    @OneToMany(mappedBy = "classId")
    private Set<Curriculum> curriculumSet;

    private LocalDate graduationDate;

}
