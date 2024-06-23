package com.egradebook.eGradeBook;


import com.egradebook.eGradeBook.entities.Principal;
import com.egradebook.eGradeBook.entities.Qualification;
import com.egradebook.eGradeBook.entities.Role;
import com.egradebook.eGradeBook.entities.School;
import com.egradebook.eGradeBook.entities.Teacher;
import com.egradebook.eGradeBook.entities.User;
import com.egradebook.eGradeBook.repositories.PrincipalRepository;
import com.egradebook.eGradeBook.repositories.QualificationsRepository;
import com.egradebook.eGradeBook.repositories.RoleRepository;
import com.egradebook.eGradeBook.repositories.SchoolRepository;
import com.egradebook.eGradeBook.repositories.TeacherRepository;
import com.egradebook.eGradeBook.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.swing.*;
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

	@Component
	public class DefaultAccount implements CommandLineRunner {

		private final RoleRepository roleRepository;
		private final UserRepository userRepository;
		private final QualificationsRepository qualificationsRepository;
		private final PrincipalRepository principalRepository;
		private final SchoolRepository schoolRepository;
		private final TeacherRepository teacherRepository;
		private PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		public DefaultAccount(RoleRepository roleRepository, UserRepository userRepository, QualificationsRepository qualificationsRepository, PrincipalRepository principalRepository, SchoolRepository schoolRepository, TeacherRepository teacherRepository, PasswordEncoder bCryptPasswordEncoder) {
			this.roleRepository = roleRepository;
			this.userRepository = userRepository;
            this.qualificationsRepository = qualificationsRepository;
            this.principalRepository = principalRepository;
            this.schoolRepository = schoolRepository;
            this.teacherRepository = teacherRepository;
            this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        }

		@Override
		public void run(String... args) throws Exception {

			createRoles(roleRepository);
			createQualifications(qualificationsRepository);
			createPrincipals(principalRepository, roleRepository, bCryptPasswordEncoder);
			createSchools(schoolRepository, principalRepository);
			createTeachers(teacherRepository, roleRepository, bCryptPasswordEncoder);
			createAdmin(userRepository, roleRepository, bCryptPasswordEncoder);

		}

		private static void createRoles(RoleRepository roleRepository) {

			Role admin = new Role("admin");
			Role teacher = new Role("teacher");
			Role student = new Role("student");
			Role parent = new Role("parent");
			Role principal = new Role("principal");

			roleRepository.save(admin);
			roleRepository.save(teacher);
			roleRepository.save(student);
			roleRepository.save(parent);
			roleRepository.save(principal);
		}

		private static void	createAdmin(UserRepository userRepository,
										   RoleRepository roleRepository,
										   PasswordEncoder passwordEncoder) {

			Role admin = roleRepository.findByName("ADMIN").orElse(null);
			Set<Role> roles = new HashSet<>();
			roles.add(admin);

			User user = User.builder()
			.firstName("Root")
			.lastName("Admin")
			.phoneNumber("1234567890")
			.passwordHash(passwordEncoder.encode("admin"))
			.enabled(true)
			.address("Bulgaria XYZ")
			.email("admin@example.com")
			.roles(roles)
			.build();

			userRepository.save(user);
		}

		private static void createQualifications(QualificationsRepository qualificationsRepository) {

			Qualification java = new Qualification("Java");
			Qualification cplus = new Qualification("C++");
			Qualification algorithms = new Qualification("Algorithms");
			Qualification javascript = new Qualification("JavaScript");
			Qualification htmlAndCss = new Qualification("HTML and CSS");
			Qualification operatingSystems = new Qualification("Operating Systems");
			Qualification maths = new Qualification("Maths");
			Qualification english = new Qualification("English");

			qualificationsRepository.save(java);
			qualificationsRepository.save(cplus);
			qualificationsRepository.save(algorithms);
			qualificationsRepository.save(javascript);
			qualificationsRepository.save(htmlAndCss);
			qualificationsRepository.save(operatingSystems);
			qualificationsRepository.save(maths);
			qualificationsRepository.save(english);
		}

		private static void createPrincipals(PrincipalRepository principalRepository,
											 RoleRepository roleRepository,
											 PasswordEncoder passwordEncoder) {

			Role principal = roleRepository.findByName("PRINCIPAL").orElse(null);
			Set<Role> roles = new HashSet<>();

			roles.add(principal);

			Principal johnDoe = new Principal();
			johnDoe.setFirstName("John");
			johnDoe.setLastName("Doe");
			johnDoe.setAddress("XYZ BG");
			johnDoe.setEmail("johndoe@example.com");
			johnDoe.setPasswordHash(passwordEncoder.encode("john"));
			johnDoe.setPhoneNumber("0123456789");
			johnDoe.setEnabled(true);
			johnDoe.setRoles(roles);

			Principal janeSmith = new Principal();
			janeSmith.setFirstName("Jane");
			janeSmith.setLastName("Smith");
			janeSmith.setAddress("ABC BG");
			janeSmith.setEmail("jane@example.com");
			janeSmith.setPasswordHash(passwordEncoder.encode("jane"));
			janeSmith.setPhoneNumber("9876543210");
			janeSmith.setEnabled(true);
			janeSmith.setRoles(roles);

		}

		private static void createSchools(SchoolRepository schoolRepository,
										  PrincipalRepository principalRepository) {

			Principal johnDoe = principalRepository.findPrincipalByEmail("johndoe@example.com").orElse(null);
			Principal janeSmith = principalRepository.findPrincipalByEmail("jane@example.com").orElse(null);

			School nbu = new School("NBU", "Monte Video", johnDoe);
			School telerik = new School("Telerik", "Krustiu Rakovski", janeSmith);

			schoolRepository.save(nbu);
			schoolRepository.save(telerik);

		}

		private static void createTeachers(TeacherRepository teacherRepository,
										   RoleRepository roleRepository,
										   PasswordEncoder passwordEncoder) {

			Role teacherRole = roleRepository.findByName("TEACHER").orElse(null);
			Set<Role> roles = new HashSet<>();
			roles.add(teacherRole);

			Teacher catalina = new Teacher();
			catalina.setFirstName("Catalina");
			catalina.setLastName("Dupe");
			catalina.setAddress("ABC BG");
			catalina.setEmail("catalina@example.com");
			catalina.setPasswordHash(passwordEncoder.encode("catalina"));
			catalina.setPhoneNumber("1928374589");
			catalina.setEnabled(true);
			catalina.setRoles(roles);

			Teacher mae = new Teacher();
			mae.setFirstName("Mae");
			mae.setLastName("Mahesh");
			mae.setAddress("ABC BG");
			mae.setEmail("mae@example.com");
			mae.setPasswordHash(passwordEncoder.encode("mae"));
			mae.setPhoneNumber("9685746573");
			mae.setEnabled(true);
			mae.setRoles(roles);

			teacherRepository.save(catalina);
			teacherRepository.save(mae);
		}

	}

}
