package com.egradebook.eGradeBook.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "parents")
@PrimaryKeyJoinColumn(name = "users_user_id")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Parent extends User
{
}
