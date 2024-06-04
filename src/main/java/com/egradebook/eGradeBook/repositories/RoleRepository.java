package com.egradebook.eGradeBook.repositories;

import com.egradebook.eGradeBook.DTOs.RoleDTO;
import com.egradebook.eGradeBook.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>
{
    Optional<Role> findByName(String name);

    @Query("SELECT new com.egradebook.eGradeBook.DTOs.RoleDTO(r.id, r.name) FROM User u JOIN u.roles r WHERE u.id = :userID")
    Set<RoleDTO> findRolesByUser(@Param("userID") Long id);
}
