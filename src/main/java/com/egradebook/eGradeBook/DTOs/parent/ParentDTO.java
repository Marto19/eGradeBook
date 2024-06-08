package com.egradebook.eGradeBook.DTOs.parent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParentDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;

}
