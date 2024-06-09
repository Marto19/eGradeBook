package com.egradebook.eGradeBook.entities;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "parents")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Parent
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "users_user_id", nullable = false)
    private User user;
}