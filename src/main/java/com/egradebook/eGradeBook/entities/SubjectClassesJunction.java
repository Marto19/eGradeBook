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
    private Teacher teacher;

    //TODO: TEACHERS AND THIS TABLE HAVE DUAL CONNECTION. NEED TO FIX THIS

}
