package com.egradebook.eGradeBook.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;


@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "First name cannot be blank!")
    @Size(max = 30, message = "First name has to be up to 30 characters!")
    @Column(name = "first_name", nullable = false, length = 50)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only letters!")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank!")
    @Size(max = 30, message = "Last name has to be up to 30 characters!")
    @Column(name = "last_name", nullable = false, length = 50)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only letters!")
    private String lastName;

    @NotBlank(message = "Address cannot be blank!")
    @Size(max = 30, message = "Address has to be up to 30 characters!")
    @Column(name = "address")
    @Pattern(regexp = "^[a-zA-Z0-9\\s,]+$", message = "Address must contain only letters, numbers, spaces, and commas!")
    private String address;

    @NotBlank(message = "Email cannot be blank!")
    @Column(name = "email")
    @Pattern(regexp = "^[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-zA-Z]{1,8}$", message = "Invalid email address!")
    private String email;   //email associated with the user with the account

    @Column(name = "user_password_hash", nullable = false, length = 60)
    private String passwordHash;

    @Size(max = 10, message = "Phone number has to be up to 10 digits!")
    @Column(name = "phone_number")
    @Pattern(regexp = "^[0-9]+$", message = "Phone number must contain only digits!")
    private String phoneNumber;

    @Column(name = "user_enabled", nullable = false, columnDefinition = "tinyint(1) default false")
    private Boolean enabled;

    @ManyToMany
    @JoinTable(
            name = "user_has_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

}
