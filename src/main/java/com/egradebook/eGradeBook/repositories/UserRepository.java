package com.egradebook.eGradeBook.repositories;

import org.hibernate.boot.archive.internal.JarProtocolArchiveDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;
import com.egradebook.eGradeBook.entities.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsById(long id);
    User save(User user);
}
