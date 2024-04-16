package com.egradebook.eGradeBook.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "teachers")
@PrimaryKeyJoinColumn(name = "users_user_id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Teacher extends User
{
    @OneToOne
    private User userId;

    @ManyToOne
    private School school;

    @OneToOne
    private SubjectClassesJunction subjectClassesJunction;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(name = "teachers_qualficiations",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "qualifications_id"))
    private Set<Qualification> qualificationSet;
}
