package com.egradebook.eGradeBook.services;

import com.egradebook.eGradeBook.DTOs.principal.PrincipalDTO;
import com.egradebook.eGradeBook.entities.Principal;

import java.util.List;

public interface PrincipalService
{
    void saveOrUpdate(Principal principal);
    Principal getPrincipalById(Long id);
    List<PrincipalDTO> getPrincipalDTOList();
    void softDeletePrincipal(Principal principal);
}
