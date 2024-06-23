package com.egradebook.eGradeBook.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "qualifications")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Qualification
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Qualification signature cannot be blank")
    @Size(max = 20, message = "Qualification signature has to be up to 20 characters!")
    private String signature;

    private String description;

    @ManyToMany(mappedBy = "qualificationSet")
    private Set<Teacher> teacherSet;

    public Qualification(String signature) {
        this.signature = signature;
    }

    public Qualification(String signature, String description) {
        this.signature = signature;
        this.description = description;
    }
}
