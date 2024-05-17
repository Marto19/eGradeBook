package com.egradebook.eGradeBook.DTOs;

public class UserDTO {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phoneNumber;
    private String password;
    private boolean enabled;
    private Long authorities;


    public UserDTO() {

    }

    public UserDTO(String firstName, String lastName, String address, String email, String phoneNumber, String password, boolean enabled, long authoritiesId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authoritiesId;
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

    public Long getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Long authorities_id) {
        this.authorities = authorities_id;
    }
}
