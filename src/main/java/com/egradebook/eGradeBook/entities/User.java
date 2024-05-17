package com.egradebook.eGradeBook.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "First name cannot be blank!")
    @Size(max = 30, message = "First name has to be up to 30 characters!")
    @Column(name = "first_name")
    //NEED TO ADD REGEX VALIDATION
    private String firstName;

    @NotBlank(message = "Last name cannot be blank!")
    @Size(max = 30, message = "Last name has to be up to 30 characters!")
    @Column(name = "last_name")
    //NEED TO ADD REGEX VALIDATION
    private String lastName;

    @NotBlank(message = "Last name cannot be blank!")
    @Size(max = 30, message = "Last name has to be up to 30 characters!")
    @Column(name = "address")
    //NEED TO ADD REGEX VALIDATION
    private String address;

    @NotBlank(message = "T1p lin si?")  //todo : remove
    //NEED TO ADD REGEX VALIDATION
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Authorities getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Authorities authorities) {
        this.authorities = authorities;
    }

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
