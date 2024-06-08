package com.egradebook.eGradeBook.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "principals")
@PrimaryKeyJoinColumn(name = "users_user_id")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Principal extends User
{
}
