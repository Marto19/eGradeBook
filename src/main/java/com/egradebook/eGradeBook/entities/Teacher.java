package com.egradebook.eGradeBook.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;


@ToString
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

@Entity
@Table(name = "teachers")
@PrimaryKeyJoinColumn(name = "users_user_id")
public class Teacher extends User
{
    @ManyToOne
    private School school;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(name = "teachers_qualficiations",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "qualifications_id"))
    private Set<Qualification> qualificationSet;

    @OneToMany(mappedBy = "teacherId")
    private Set<Curriculum> curriculumSet;

    @OneToMany(mappedBy = "teacherId")
    private Set<Grade> gradeSet;
}
