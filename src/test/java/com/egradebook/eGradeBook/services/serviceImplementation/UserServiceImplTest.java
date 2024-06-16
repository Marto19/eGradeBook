package com.egradebook.eGradeBook.services.serviceImplementation;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.egradebook.eGradeBook.DTOs.role.RoleDTO;
import com.egradebook.eGradeBook.DTOs.user.AuthUserDTO;
import com.egradebook.eGradeBook.DTOs.user.CreateUserDTO;
import com.egradebook.eGradeBook.DTOs.user.UpdateUserDTO;
import com.egradebook.eGradeBook.DTOs.user.UserDTO;
import com.egradebook.eGradeBook.entities.Role;
import com.egradebook.eGradeBook.entities.User;
import com.egradebook.eGradeBook.exceptions.EntityAlreadyExistsException;
import com.egradebook.eGradeBook.exceptions.InvalidRoleException;
import com.egradebook.eGradeBook.exceptions.InvalidUserException;
import com.egradebook.eGradeBook.exceptions.UserNotFoundException;
import com.egradebook.eGradeBook.repositories.RoleRepository;
import com.egradebook.eGradeBook.repositories.UserRepository;
import com.egradebook.eGradeBook.services.serviceImplementation.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser_Success() throws InvalidRoleException {
        CreateUserDTO createUserDTO = new CreateUserDTO("John", "Doe", "john@example.com", "password", "123 Street", "1234567890", "ROLE_USER");

        when(userRepository.existsByEmail(createUserDTO.getEmail())).thenReturn(false);
        when(userRepository.existsByPhoneNumber(createUserDTO.getPhoneNumber())).thenReturn(false);
        when(passwordEncoder.encode(createUserDTO.getPassword())).thenReturn("encodedPassword");
        when(roleRepository.findByName(createUserDTO.getRole())).thenReturn(Optional.of(new Role("ROLE_USER")));

        userService.createUser(createUserDTO);

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testCreateUser_UserAlreadyExists() {
        CreateUserDTO createUserDTO = new CreateUserDTO("John", "Doe", "john@example.com", "password", "123 Street", "1234567890", "ROLE_USER");

        when(userRepository.existsByEmail(createUserDTO.getEmail())).thenReturn(true);

        assertThrows(EntityAlreadyExistsException.class, () -> {
            userService.createUser(createUserDTO);
        });
    }

    @Test
    public void testUpdateUser_Success() throws InvalidUserException, InvalidRoleException {
        UpdateUserDTO updateUserDTO = new UpdateUserDTO("John", "Doe", "123 Street", "john@example.com", "newPassword", "1234567890", Set.of("ROLE_USER"), true);
        User user = new User();
        user.setEmail("john@example.com");

        when(userRepository.findUserByEmail(updateUserDTO.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.encode(updateUserDTO.getPassword())).thenReturn("encodedNewPassword");
        when(roleRepository.findByName(anyString())).thenReturn(Optional.of(new Role("ROLE_USER")));

        userService.updateUser(updateUserDTO);

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testUpdateUser_UserNotFound() {
        UpdateUserDTO updateUserDTO = new UpdateUserDTO("John", "Doe", "123 Street", "john@example.com", "newPassword", "1234567890", Set.of("ROLE_USER"), true);

        when(userRepository.findUserByEmail(updateUserDTO.getEmail())).thenReturn(Optional.empty());

        assertThrows(InvalidUserException.class, () -> {
            userService.updateUser(updateUserDTO);
        });
    }

    @Test
    public void testDeleteUser_Success() throws UserNotFoundException {
        User user = new User();
        user.setEmail("john@example.com");
        user.setEnabled(true);

        when(userRepository.findUserByEmail("john@example.com")).thenReturn(Optional.of(user));

        userService.deleteUser("john@example.com");

        verify(userRepository, times(1)).save(user);
        assertFalse(user.getEnabled());
    }

    @Test
    public void testDeleteUser_UserNotFound() {
        when(userRepository.findUserByEmail("john@example.com")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userService.deleteUser("john@example.com");
        });
    }

    ///////////////
    @Test
    void testGetAllUsers() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setAddress("123 Street");
        user.setEmail("john.doe@example.com");
        user.setPasswordHash("hashedPassword");
        user.setPhoneNumber("1234567890");
        user.setRoles(Collections.singleton(new Role("ROLE_USER")));
        user.setEnabled(true);
        users.add(user);

        when(userRepository.findAll()).thenReturn(users);

        List<UpdateUserDTO> result = userService.getAllUsers();

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
    }

    @Test
    void testFindUpdateUserDTOByEmail() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setAddress("123 Street");
        user.setEmail("john.doe@example.com");
        user.setPasswordHash("hashedPassword");
        user.setPhoneNumber("1234567890");
        user.setRoles(Collections.singleton(new Role("ROLE_USER")));
        user.setEnabled(true);

        when(userRepository.findUserByEmail(anyString())).thenReturn(Optional.of(user));

        Optional<UpdateUserDTO> result = userService.findUpdateUserDTOByEmail("john.doe@example.com");

        assertTrue(result.isPresent());
        assertEquals("John", result.get().getFirstName());
    }

    @Test
    void testFindByEmail() {
        AuthUserDTO authUserDTO = new AuthUserDTO(1L, "john.doe@example.com", "John", "Doe", "hashedPassword", true);

        when(userRepository.findAuthUserByEmail(anyString())).thenReturn(Optional.of(authUserDTO));

        AuthUserDTO result = userService.findByEmail("john.doe@example.com");

        assertEquals("john.doe@example.com", result.getEmail());
    }

    @Test
    void testFindByEmail_UserNotFound() {
        when(userRepository.findAuthUserByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userService.findByEmail("john.doe@example.com"));
    }

    @Test
    void testLoadUserByUsername() {
        AuthUserDTO authUserDTO = new AuthUserDTO(1L, "john.doe@example.com", "John", "Doe", "hashedPassword", true);

        when(userRepository.findAuthUserByEmail(anyString())).thenReturn(Optional.of(authUserDTO));
        when(roleRepository.findRolesByUser(anyLong())).thenReturn(Collections.singleton(new RoleDTO(1L, "ROLE_USER")));

        UserDetails userDetails = userService.loadUserByUsername("john.doe@example.com");

        assertEquals("john.doe@example.com", userDetails.getUsername());
        assertEquals("hashedPassword", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Test
    void testGetAllUsersDto() {
        List<UserDTO> userDTOs = new ArrayList<>();
        UserDTO userDTO = new UserDTO(1L, "John", "Doe", "john.doe@example.com", "123 Street", "1234567890");
        userDTOs.add(userDTO);

        when(userRepository.findAllUserDTO()).thenReturn(userDTOs);

        List<UserDTO> result = userService.getAllUsersDto();

        assertEquals(1, result.size());
        assertEquals("john.doe@example.com", result.get(0).getEmail());
    }

    @Test
    void testFindById() throws UserNotFoundException {
        User user = new User();
        user.setId(1L);
        user.setEmail("john.doe@example.com");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        User result = userService.findById(1L);

        assertEquals(1L, result.getId());
        assertEquals("john.doe@example.com", result.getEmail());
    }

    @Test
    void testFindById_UserNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.findById(1L));
    }
}
