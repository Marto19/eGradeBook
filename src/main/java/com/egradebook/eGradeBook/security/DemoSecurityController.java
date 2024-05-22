package com.egradebook.eGradeBook.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoSecurityController {

    @GetMapping("/hello")
    @PreAuthorize("hasRole('client_user')") //IMPORTANT: This is the role that we have defined in the Keycloak server - the client roles
    public String hello() {
        return "Hello, security!";
    }

    @GetMapping("/hello2")
    @PreAuthorize("hasRole('client_admin')")    //hasRole method uses a default prefix of ROLE_ to check for roles. So we need to conver the roles in Keycloak to ROLE_client_user and ROLE_client_admin
    public String hello2() {
        return "Hello, security - ADMIN!";
    }

}
