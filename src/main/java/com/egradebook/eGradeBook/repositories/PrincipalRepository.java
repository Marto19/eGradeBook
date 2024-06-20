package com.egradebook.eGradeBook.repositories;

import com.egradebook.eGradeBook.DTOs.principle.PrincipalDTO;
import com.egradebook.eGradeBook.DTOs.principle.PrincipalSchoolDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrincipalRepository extends JpaRepository<com.egradebook.eGradeBook.entities.Principal, Long> {

    @Query("SELECT new com.egradebook.eGradeBook.DTOs.principle.PrincipalDTO(" +
            "p.id, p.firstName, p.lastName, p.phoneNumber)" +
            "FROM Principal p")
    List<PrincipalDTO> getPrincipalDTOs();

    @Query("SELECT new com.egradebook.eGradeBook.DTOs.principle.PrincipalSchoolDTO(" +
            "p.id, p.firstName, p.lastName, p.phoneNumber, s)" +
            "FROM Principal p JOIN School s ON s.principal.id = p.id")
    List<PrincipalSchoolDTO> getPrincipalDTOsWithSchool();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO principals (users_user_id) " +
            "VALUES (:id)",
            nativeQuery = true)
    void insertPrincipal(@Param("id") Long id);

}
