package com.egradebook.eGradeBook.DTOs.school;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class SchoolCreateDTO {

    private String schoolName;
    private String schoolAddress;
    private Long principalId;

}
