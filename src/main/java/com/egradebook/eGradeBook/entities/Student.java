package com.egradebook.eGradeBook.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Student extends User
{
    @OneToOne
    private User userId;

    @ManyToOne
    private School schoolId;

    @OneToOne
    private Parent parentId;

    @ManyToOne
    private Class classID;
}
