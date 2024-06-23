package com.egradebook.eGradeBook.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;


@ToString
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

@Entity
@Table(name = "teachers")
@PrimaryKeyJoinColumn(name = "users_user_id")
@OnDelete(action = OnDeleteAction.NO_ACTION)
public class Teacher extends User
{
    // Changing the Teacher entity's school property will cascade to the associated School entity
    // Deletion of the teacher will not affect the School Entity
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private School school;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(name = "teachers_qualficiations",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "qualifications_id"))
    private Set<Qualification> qualificationSet;

    // Prevent cascade deletion
    @OneToMany(mappedBy = "teacherId", cascade = CascadeType.DETACH)
    private Set<Curriculum> curriculumSet;

    // Prevent cascade deletion
    @OneToMany(mappedBy = "teacherId", cascade = CascadeType.DETACH)
    private Set<Grade> gradeSet;

    public Teacher(String firstName, String lastName, String address, String email, String passwordHash, String phoneNumber, Boolean enabled, Set<Role> roles) {
        super(firstName, lastName, address, email, passwordHash, phoneNumber, enabled, roles);
    }
}
