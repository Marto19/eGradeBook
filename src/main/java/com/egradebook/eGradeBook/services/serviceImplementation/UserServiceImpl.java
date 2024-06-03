package com.egradebook.eGradeBook.services.serviceImplementation;

import com.egradebook.eGradeBook.DTOs.AuthUserDTO;
import com.egradebook.eGradeBook.DTOs.RoleDTO;
import com.egradebook.eGradeBook.repositories.RoleRepository;
import com.egradebook.eGradeBook.repositories.UserRepository;
import com.egradebook.eGradeBook.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * This class is used to implement the methods that will be used to interact with the User entity.
 * The methods include creating, updating, and deleting users.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public AuthUserDTO findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        AuthUserDTO authUserDTO = userRepository.findByEmail(email);

        if (authUserDTO == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        return new org.springframework.security.core.userdetails.User(
                authUserDTO.getEmail(),
                authUserDTO.getPasswordHash(),
                extractRolesFromUser(authUserDTO)
        );

    }

    private Collection<? extends GrantedAuthority> extractRolesFromUser(AuthUserDTO authUserDTO) {

        Set<RoleDTO> authorities = roleRepository.findRolesByUser(authUserDTO.getId());

        return authorities.stream().map((authority) -> new
                SimpleGrantedAuthority(authority.getName())).collect(Collectors.toSet());

    }
}
