package com.egradebook.eGradeBook.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "class_grade_number_catalog")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassGradeNumberCatalog
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int cgNumber;

    @OneToMany(mappedBy = "classGradeNumber")
    private Set<Class> classes;
}
