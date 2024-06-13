package com.egradebook.eGradeBook;


import com.egradebook.eGradeBook.entities.Role;
import com.egradebook.eGradeBook.entities.User;
import com.egradebook.eGradeBook.repositories.RoleRepository;
import com.egradebook.eGradeBook.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class EGradeBookApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(EGradeBookApplication.class, args);
	}

//	@Component
//	public class DefaultAccount implements CommandLineRunner {
//
//		private final RoleRepository roleRepository;
//		private final UserRepository userRepository;
//		private final PasswordEncoder bCryptPasswordEncoder;
//
//		public DefaultAccount(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder) {
//			this.roleRepository = roleRepository;
//			this.userRepository = userRepository;
//			this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//		}
//
//		@Override
//		public void run(String... args) throws Exception {
//			Role admin = new Role("admin");
//			Role teacher = new Role("teacher");
//			Role student = new Role("student");
//			Role parent = new Role("parent");
//			Role principal = new Role("principal");
//
//			roleRepository.save(admin);
//			roleRepository.save(teacher);
//			roleRepository.save(student);
//			roleRepository.save(parent);
//			roleRepository.save(principal);
//
//			Set<Role> roleSet = new HashSet<>();
//			roleSet.add(admin);
//
//			User user = User.builder()
//					.firstName("Root")
//					.lastName("Admin")
//					.phoneNumber("1234567890")
//					.passwordHash(bCryptPasswordEncoder.encode("admin"))
//					.enabled(true)
//					.address("Bulgaria XYZ")
//					.email("admin@example.com")
//					.roles(roleSet)
//					.build();
//
//			userRepository.save(user);
//		}
//
//	}

}
