package com.egradebook.eGradeBook.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "class_letter_catalog")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassLetterCatalog
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String cLetter;

    @OneToMany(mappedBy = "classLetterCatalog")
    private Set<Class> classes;
}
