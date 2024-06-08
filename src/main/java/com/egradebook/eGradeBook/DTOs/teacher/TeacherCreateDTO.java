package com.egradebook.eGradeBook.DTOs.teacher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@ToString
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherCreateDTO {

    private Long userId;
    private Long schoolId;
    private Set<Long> qualificationIds;
}
