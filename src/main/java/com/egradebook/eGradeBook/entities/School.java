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

    @OneToOne(cascade = CascadeType.DETACH)
    private Principal principal;

    @OneToMany
    private Set<Student> students;

    @OneToMany
    private Set<Teacher> teachers;

    @OneToMany
    private Set<Class> classes;

    public School(String name, String address, Principal principal) {
        this.name = name;
        this.address = address;
        this.principal = principal;
    }
}
