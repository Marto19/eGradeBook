package com.egradebook.eGradeBook.services.serviceImplementation;

import com.egradebook.eGradeBook.DTOs.principle.PrincipalDTO;
import com.egradebook.eGradeBook.entities.Principal;
import com.egradebook.eGradeBook.repositories.PrincipalRepository;
import com.egradebook.eGradeBook.services.PrincipalService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PrincipalServiceImpl implements PrincipalService {

    @Autowired
    private PrincipalRepository principalRepository;

    @Override
    public Principal getPrincipalById(Long id) {
        return principalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Principal with ID:" + id + " not found!"));
    }

    @Override
    public List<PrincipalDTO> getPrincipalDTOList() {
        return principalRepository.getPrincipalDTOs();
    }

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

    @Override
    public void softDeletePrincipal(Principal principal) {
        Principal existingPrincipal = principalRepository.findById(principal.getId()).orElse(null);
        if (existingPrincipal != null) {
            existingPrincipal.setEnabled(false);
            principalRepository.save(existingPrincipal);
        }
    }
}
