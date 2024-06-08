package com.egradebook.eGradeBook.services;

import com.egradebook.eGradeBook.entities.Principal;
import com.egradebook.eGradeBook.services.serviceImplementation.PrincipalServiceImpl;
import org.springframework.stereotype.Service;

@Service
public interface PrincipalService
{
    void saveOrUpdate(Principal principal);
}
