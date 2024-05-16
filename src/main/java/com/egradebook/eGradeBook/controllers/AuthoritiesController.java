package com.egradebook.eGradeBook.controllers;

import com.egradebook.eGradeBook.entities.Authorities;
import com.egradebook.eGradeBook.repositories.AuthoritiesRepository;
import com.egradebook.eGradeBook.services.AuthoritiesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


/**
 * This class is used to create the controller for the Authorities entity.
 */
@Validated
@RestController
@RequestMapping("/api/v1/authorities")
public class AuthoritiesController{

    private final AuthoritiesService authoritiesService;
    private final AuthoritiesRepository authoritiesRepository;

    public AuthoritiesController(AuthoritiesService authoritiesService, AuthoritiesRepository authoritiesRepository) {
        this.authoritiesService = authoritiesService;
        this.authoritiesRepository = authoritiesRepository;
    }

    /**
     * This method is used to create a new authority.
     * @param authority The authority object that will be created.
     * @return The authority object that was created.
     */
    @RequestMapping("/create")
    public ResponseEntity<String> createAuthority(@Valid @RequestBody Authorities authority) {
        Authorities createdAuthority = authoritiesService.createAuthority(authority);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Authority created successfully with ID: " + createdAuthority.getId());
    }

    /**
     * This method is used to update an authority.
     * @param authority The authority object that will be updated.
     * @return The authority object that was updated.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateAuthority(@PathVariable Long id, @Valid @RequestBody Authorities authority) {
        Optional<Authorities> existingAuthority = authoritiesRepository.findById(id);
        if (existingAuthority.isPresent()) {
            authority.setId(id); // set the id of the authority to be updated
            authoritiesService.updateAuthority(authority);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Authority updated successfully with ID: " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Authority with ID: " + id + " not found");
        }
    }

    /**
     * This method is used to get an authority.
     * @param id The id of the authority that will be retrieved.
     * @return The authority object that was retrieved.
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Authorities>> getAuthority(@PathVariable long id) {
        Optional<Authorities> authority = authoritiesRepository.findAuthoritiesById(id);
        return ResponseEntity.ok(authority);
    }

    /**
     * This method is used to get all authorities.
     * @return The list of authorities.
     */
    @GetMapping("/get")
    public ResponseEntity<Iterable<Authorities>> getAuthorities() {
        Iterable<Authorities> authorities = authoritiesRepository.findAll();
        return ResponseEntity.ok(authorities);
    }

    /**
     * This method is used to delete an authority.
     * @param id The id of the authority that will be deleted.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAuthority(@PathVariable long id) {
        authoritiesService.deleteAuthority(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Authority deleted successfully with ID: " + id);
    }
}
