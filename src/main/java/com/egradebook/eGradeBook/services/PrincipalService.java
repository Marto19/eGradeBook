package com.egradebook.eGradeBook.services;

import com.egradebook.eGradeBook.DTOs.principle.PrincipalDTO;
import com.egradebook.eGradeBook.entities.Principal;
import com.egradebook.eGradeBook.services.serviceImplementation.PrincipalServiceImpl;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

public interface PrincipalService
{
    void saveOrUpdate(Principal principal);
    Principal getPrincipalById(Long id);
    List<PrincipalDTO> getPrincipalDTOList();
    void softDeletePrincipal(Principal principal);

    Principal createPrincipal(Long userId) throws RoleNotFoundException;
}
