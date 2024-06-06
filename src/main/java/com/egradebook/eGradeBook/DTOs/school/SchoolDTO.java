package com.egradebook.eGradeBook.DTOs.school;

import com.egradebook.eGradeBook.entities.Principal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchoolDTO {

    private Long id;
    private String Name;
    private String Address;
}
