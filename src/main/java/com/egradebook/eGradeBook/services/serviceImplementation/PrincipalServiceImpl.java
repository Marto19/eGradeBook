package com.egradebook.eGradeBook.services.serviceImplementation;

import com.egradebook.eGradeBook.entities.Principal;
import com.egradebook.eGradeBook.repositories.PrincipalRepository;
import com.egradebook.eGradeBook.services.PrincipalService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PrincipalServiceImpl implements PrincipalService {

    @Autowired
    private PrincipalRepository principalRepository;


    @Override
    public void saveOrUpdate(Principal principal) {
        if (principal != null) {
            Principal existingPrincipal = principalRepository.findById(principal.getId()).orElse(null);
            if (existingPrincipal != null) {
                existingPrincipal.setFirstName(principal.getFirstName());
                existingPrincipal.setLastName(principal.getLastName());
                existingPrincipal.setAddress(principal.getAddress());
                existingPrincipal.setEmail(principal.getEmail());
                existingPrincipal.setPasswordHash(principal.getPasswordHash());
                existingPrincipal.setPhoneNumber(principal.getPhoneNumber());
                existingPrincipal.setEnabled(principal.getEnabled());
                existingPrincipal.setRoles(principal.getRoles());
                principalRepository.save(existingPrincipal);
            } else {
                // Save the new principal
                principalRepository.save(principal);
            }
        }
    }
}
