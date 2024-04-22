package com.egradebook.eGradeBook.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "parents")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Parent extends User
{
}
