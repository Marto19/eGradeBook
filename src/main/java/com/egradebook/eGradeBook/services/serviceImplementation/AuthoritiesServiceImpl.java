package com.egradebook.eGradeBook.services.serviceImplementation;

import com.egradebook.eGradeBook.entities.Authorities;
import com.egradebook.eGradeBook.repositories.AuthoritiesRepository;
import com.egradebook.eGradeBook.services.AuthoritiesService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * This class is used to implement the methods that will be used to interact with the Authorities entity.
 */
@Service
@AllArgsConstructor
public class AuthoritiesServiceImpl implements AuthoritiesService {
    private final AuthoritiesRepository authoritiesRepository;

    /**
     * This method is used to create a new authority.
     * @param authority The authority object that will be created.
     * @return The authority object that was created.
     */
    @Override
    public Authorities createAuthority(Authorities authority) {
        if (authority == null) {
            throw new IllegalArgumentException("Authority cannot be null");
        }
        if (authoritiesRepository.findByAuthority(authority.getRoleName()).isPresent()) {
            throw new IllegalArgumentException("Authority already exists");
        }
        //TODO: add more  checks in the future and review these
        return authoritiesRepository.save(authority);
    }

    /**
     * This method is used to update an authority.
     * @param authority The authority object that will be updated.
     * @return The authority object that was updated.
     */
    @Override
    public void updateAuthority(Authorities authority) {
        //if authority is null, throw an exception
        if (authority == null) {
            throw new IllegalArgumentException("Authority cannot be null");
        }
        //if authority does not exist, throw an exception
        // fetch the existing authority, update the fields, and then save it.
        Optional<Authorities> existingAuthority = authoritiesRepository.findById(authority.getId());
        if (!existingAuthority.isPresent()) {
            throw new EntityNotFoundException("Authority does not exist");
        }
        existingAuthority.get().setRoleName(authority.getRoleName());
        // set other fields that you want to update
        authoritiesRepository.save(existingAuthority.get());
    }

    /**
     * This method is used to delete an authority.
     * @param id The id of the authority that will be deleted.
     */
    @Override
    public void deleteAuthority(long id) {
        Optional<Authorities> authority = authoritiesRepository.findById(id);
        if (!authority.isPresent()) {
            throw new EntityNotFoundException("Authority not found with id " + id);
        }
        authoritiesRepository.delete(authority.get());
    }

}
