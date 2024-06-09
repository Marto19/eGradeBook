package com.egradebook.eGradeBook.repositories;

import com.egradebook.eGradeBook.DTOs.parent.ParentDTO;
import com.egradebook.eGradeBook.entities.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {

    @Query("SELECT new com.egradebook.eGradeBook.DTOs.parent.ParentDTO(" +
            "p.id, p.user.firstName, p.user.lastName, p.user.phoneNumber)" +
            "FROM Parent p")
    List<ParentDTO> getParentsDTO();

    @Query("SELECT p.user.id FROM Parent p")
    List<Long> getParentUserIds();

    boolean existsByUserId(Long userId);
}
