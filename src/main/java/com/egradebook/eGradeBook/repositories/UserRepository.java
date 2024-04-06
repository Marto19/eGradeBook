package com.egradebook.eGradeBook.repositories;

import org.hibernate.boot.archive.internal.JarProtocolArchiveDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsById(long id);

    com.egradebook.eGradeBook.entities.User save(com.egradebook.eGradeBook.entities.User user);
}
