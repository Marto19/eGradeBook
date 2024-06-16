package com.egradebook.eGradeBook.DTOs.classes;


import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchoolClassesNoId {

    private String schoolName;
    private String classLetter;
    private int classGrade;
}
