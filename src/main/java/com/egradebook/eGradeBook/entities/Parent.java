package com.egradebook.eGradeBook.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "parents")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Parent extends User
{
    //TRQBVA LI NI DPULNITELNO ID POLE

    @OneToOne
    private User userId;

    @Override
    public String toString() {
        return "Parent{" +
                "userId=" + userId +
                '}';
    }
}
