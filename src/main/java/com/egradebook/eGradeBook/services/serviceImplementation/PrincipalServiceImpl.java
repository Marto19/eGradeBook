package com.egradebook.eGradeBook.services.serviceImplementation;

import com.egradebook.eGradeBook.DTOs.principle.PrincipalDTO;
import com.egradebook.eGradeBook.entities.Principal;
import com.egradebook.eGradeBook.repositories.PrincipalRepository;
import com.egradebook.eGradeBook.repositories.RoleRepository;
import com.egradebook.eGradeBook.repositories.UserRepository;
import com.egradebook.eGradeBook.services.PrincipalService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.egradebook.eGradeBook.entities.User;
import com.egradebook.eGradeBook.entities.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class PrincipalServiceImpl implements PrincipalService {

    @Autowired
    private PrincipalRepository principalRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private static final Logger logger = LoggerFactory.getLogger(PrincipalServiceImpl.class);

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

    @Override
    public Principal createPrincipal(Long userId) throws RoleNotFoundException {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            logger.error("User not found with ID: {}", userId);
            throw new EntityNotFoundException("User not found");
        }

        Role principalRole = roleRepository.findByName("principal")
                .orElseThrow(() -> new RoleNotFoundException("Role principal was not found"));
        user.getRoles().add(principalRole);

        Principal principal = new Principal();
        principal.setId(userId);
        principal.setFirstName(user.getFirstName());
        principal.setLastName(user.getLastName());
        principal.setAddress(user.getAddress());
        principal.setEmail(user.getEmail());
        principal.setPasswordHash(user.getPasswordHash());
        principal.setPhoneNumber(user.getPhoneNumber());
        principal.setEnabled(user.getEnabled());
        principal.setRoles(user.getRoles());

        principalRepository.insertPrincipal(userId); // This is crucial
        logger.info("Successfully created principal with ID: {}", principal.getId());
        return principal;
    }
}
