package com.egradebook.eGradeBook.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GradeDTO {
    private long id;
    private long gradeCatalogId;
    private long subjectId;
    private long studentId;
    private long teacherId;
}
