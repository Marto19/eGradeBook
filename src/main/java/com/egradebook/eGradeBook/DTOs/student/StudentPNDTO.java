package com.egradebook.eGradeBook.DTOs.student;

import com.egradebook.eGradeBook.DTOs.AbsenceDTO;
import com.egradebook.eGradeBook.DTOs.GradeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentPNDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phoneNumber;
    private Set<GradeDTO> gradeSet;
    private Set<AbsenceDTO> absencesSet;
}
