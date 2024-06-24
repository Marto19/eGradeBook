package com.egradebook.eGradeBook.DTOs.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
@AllArgsConstructor
public class StudentSummaryDTO {

    private Long id;
    private String firstName;
    private String lastName;

}
