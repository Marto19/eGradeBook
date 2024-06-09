package com.egradebook.eGradeBook.services;

import com.egradebook.eGradeBook.DTOs.principle.PrincipalDTO;
import com.egradebook.eGradeBook.entities.Principal;
import com.egradebook.eGradeBook.services.serviceImplementation.PrincipalServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PrincipalService
{
    void saveOrUpdate(Principal principal);

    Principal getPrincipalById(Long id);
    List<PrincipalDTO> getPrincipalDTOList();
}
