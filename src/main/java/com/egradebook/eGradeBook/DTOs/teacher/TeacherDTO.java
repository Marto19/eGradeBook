package com.egradebook.eGradeBook.DTOs.teacher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
