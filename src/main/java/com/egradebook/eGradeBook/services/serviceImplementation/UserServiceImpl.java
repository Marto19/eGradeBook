package com.egradebook.eGradeBook.services.serviceImplementation;

import com.egradebook.eGradeBook.DTOs.user.AuthUserDTO;
import com.egradebook.eGradeBook.DTOs.RoleDTO;
import com.egradebook.eGradeBook.DTOs.user.UserDTO;
import com.egradebook.eGradeBook.entities.Role;
import com.egradebook.eGradeBook.entities.User;
import com.egradebook.eGradeBook.exceptions.InvalidRoleException;
import com.egradebook.eGradeBook.repositories.RoleRepository;
import com.egradebook.eGradeBook.repositories.UserRepository;
import com.egradebook.eGradeBook.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * This class is used to implement the methods that will be used to interact with the User entity.
 * The methods include creating, updating, and deleting users.
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthUserDTO findByEmail(String email)
    {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));
    }

    @Override
    public void createUser(UserDTO userDto)
    {
        User newUser = User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .passwordHash(passwordEncoder.encode(userDto.getPasswordHash()))
                .address(userDto.getAddress())
                .phoneNumber(userDto.getPhoneNumber())
                .enabled(true)
                .build();

        Role userRole = roleRepository.findByName("user")
                .orElseThrow(() -> new InvalidRoleException("Role not found"));

        newUser.setRoles(Set.of(userRole));

        userRepository.save(newUser);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        AuthUserDTO authUserDTO = findByEmail(email);

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
