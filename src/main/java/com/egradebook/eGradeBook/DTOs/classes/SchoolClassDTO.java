package com.egradebook.eGradeBook.DTOs.classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchoolClassDTO {

    private Long schoolId;
    private String schoolName;
    private String classLetter;
    private String classGrade;
}
