package com.egradebook.eGradeBook.repositories;

import com.egradebook.eGradeBook.entities.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {
    Optional<Authorities> findAuthoritiesById(long id);

    Optional<Authorities> findByRoleName(String roleName);

}
