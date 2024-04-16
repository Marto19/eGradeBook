package com.egradebook.eGradeBook.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "authorities")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Authorities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Role name cannot be blank!")
    @Size(max = 30, message = "Role name has to be up to 30 characters!")
    @Column(name = "role_name")
    private String roleName;

    @OneToMany(mappedBy = "authorities")    //todo: maybe make it many to many
    private Set<User> users;

    @Override
    public String toString() {
        return "Authorities{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
