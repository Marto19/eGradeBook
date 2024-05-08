package com.egradebook.eGradeBook.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "subject_classes_junction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectClassesJunction {

    @EmbeddedId
    private SubjectClassId subjectClassId;

    @OneToOne
    @JoinColumn(name = "teachers_users_user_id")
    private Teacher teacher;
}
