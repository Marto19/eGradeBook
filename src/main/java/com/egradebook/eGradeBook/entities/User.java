package com.egradebook.eGradeBook.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class User
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "First name cannot be blank!")
    @Size(max = 30, message = "First name has to be up to 30 characters!")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank!")
    @Size(max = 30, message = "Last name has to be up to 30 characters!")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Last name cannot be blank!")
    @Size(max = 30, message = "Last name has to be up to 30 characters!")
    @Column(name = "address")
    private String address;

    @NotBlank(message = "T1p lin si?")
    private String email;   //email associated with the user with the account

    @Size(max = 10, message = "Phone number has to be up to 10 digits!")
    private String phoneNumber;

    @NotBlank(message = "Password cannot be blank!")
    @Size(max = 30, message = "Last name has to be up to 30 characters!")
    private String password;

    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "authorities_id")
    private Authorities authorities;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
