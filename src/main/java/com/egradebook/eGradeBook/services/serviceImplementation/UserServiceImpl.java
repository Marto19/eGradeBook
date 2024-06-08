package com.egradebook.eGradeBook.services.serviceImplementation;

import com.egradebook.eGradeBook.DTOs.user.AuthUserDTO;
import com.egradebook.eGradeBook.DTOs.RoleDTO;
import com.egradebook.eGradeBook.DTOs.user.CreateUserDTO;
import com.egradebook.eGradeBook.DTOs.user.UpdateUserDTO;
import com.egradebook.eGradeBook.DTOs.user.UserDTO;
import com.egradebook.eGradeBook.entities.Role;
import com.egradebook.eGradeBook.entities.User;
import com.egradebook.eGradeBook.exceptions.EntityAlreadyExistsException;
import com.egradebook.eGradeBook.exceptions.InvalidRoleException;
import com.egradebook.eGradeBook.exceptions.InvalidUserException;
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
import java.util.List;
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
        return userRepository.findAuthUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));
    }

    @Override
    public void createUser(CreateUserDTO createUserDto) throws InvalidRoleException
    {
        if (!userRepository.existsByEmail(createUserDto.getEmail()) && !userRepository.existsByPhoneNumber(createUserDto.getPhoneNumber()))
        {

            User newUser = User.builder()
                    .firstName(createUserDto.getFirstName())
                    .lastName(createUserDto.getLastName())
                    .email(createUserDto.getEmail())
                    .passwordHash(passwordEncoder.encode(createUserDto.getPasswordHash()))
                    .address(createUserDto.getAddress())
                    .phoneNumber(createUserDto.getPhoneNumber())
                    .enabled(true)
                    .build();

            Role userRole = roleRepository.findByName("student")
                    .orElseThrow(() -> new InvalidRoleException("Role not found"));

            newUser.setRoles(Set.of(userRole));

            userRepository.save(newUser);
        }
        else
        {
            throw new EntityAlreadyExistsException("User already exists");
        }
    }

    @Override
    public void updateUser(UpdateUserDTO updateUserDTO) throws InvalidUserException
    {
        User existingUser = userRepository.findUserByEmail(updateUserDTO.getEmail())
                .orElseThrow(() -> new InvalidUserException("User does not exists"));

        existingUser.setFirstName(updateUserDTO.getFirstName());
        existingUser.setLastName(updateUserDTO.getLastName());
        existingUser.setAddress(updateUserDTO.getAddress());
        existingUser.setPhoneNumber(updateUserDTO.getPhoneNumber());

        if (updateUserDTO.getPasswordHash() != null && !updateUserDTO.getPasswordHash().isEmpty()) {
            existingUser.setPasswordHash(passwordEncoder.encode(updateUserDTO.getPasswordHash()));
        }

        Set<Role> roles = updateUserDTO.getRoles().stream()
                .map(roleDTO -> roleRepository.findByName(roleDTO.getName())
                        .orElseThrow(() -> new InvalidRoleException("Role not found: " + roleDTO.getName())))
                .collect(Collectors.toSet());

        existingUser.setRoles(roles);

        userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(String email)
    {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new InvalidUserException("User Not Found with email: " + email));

        userRepository.delete(user);
    }

    @Override
    public List<UpdateUserDTO> getAllUsers()
    {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> new UpdateUserDTO(
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getPasswordHash(),
                        user.getAddress(),
                        user.getPhoneNumber(),
                        user.getRoles().stream()
                                .map(role -> new RoleDTO(role.getId(), role.getName()))
                                .collect(Collectors.toSet())))
                .collect(Collectors.toList());
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

    @Override
    public List<UserDTO> getAllUsersDto() {
        return userRepository.findAllUserDTO();
    }
}
