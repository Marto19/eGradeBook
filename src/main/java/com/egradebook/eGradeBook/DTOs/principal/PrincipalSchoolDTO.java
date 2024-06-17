package com.egradebook.eGradeBook.DTOs.principal;

import com.egradebook.eGradeBook.entities.School;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrincipalSchoolDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private School school;

}
