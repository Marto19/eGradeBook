package com.egradebook.eGradeBook.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//The @JsonIdentityInfo annotation helps in handling circular references caused by bidirectional
// relationships in our entities.
public class Authorities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Role name cannot be blank!")
    @Size(max = 30, message = "Role name has to be up to 30 characters!")
    @Column(name = "role_name")
    private String roleName;

    @OneToMany(mappedBy = "authorities")    //todo: maybe make it many to many
//    @JsonBackReference
    private Set<User> users;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(@NotBlank(message = "Role name cannot be blank!") @Size(max = 30, message = "Role name has to be up to 30 characters!") String roleName) {
        this.roleName = roleName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


    @Override
    public String toString() {
        return "Authorities{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
