package com.egradebook.eGradeBook.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "principals")
@PrimaryKeyJoinColumn(name = "users_user_id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Principal extends User
{

    //TRQBVA LI NI DPULNITELNO ID POLE
    @OneToOne
    private User userId;

    @Override
    public String toString() {
        return "Principal{" +
                ", userId=" + userId +
                '}';
    }
}
