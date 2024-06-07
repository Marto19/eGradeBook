package com.egradebook.eGradeBook.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "schools")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class School
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "School name cannot be blank!")
    //REGEX FOR VALIDATION
    private String name;

    @NotBlank(message = "School address cannot be blank!")
    //REGEX FOR VALIDATION
    private String address;

    @OneToOne
    private Principal principal;

    @OneToMany
    private Set<Student> students;

    @OneToMany
    private Set<Teacher> teachers;

    @OneToMany
    private Set<Class> classes;

}
