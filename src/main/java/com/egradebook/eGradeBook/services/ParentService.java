package com.egradebook.eGradeBook.services;

import com.egradebook.eGradeBook.DTOs.parent.ParentDTO;
import com.egradebook.eGradeBook.entities.Parent;
import com.egradebook.eGradeBook.exceptions.ParentNotFoundException;

import java.util.List;

public interface ParentService {

    Parent updateParent(Parent parent);
    Parent findById(Long id) throws ParentNotFoundException;

    ParentDTO findParentDTOById(Long id);

    List<ParentDTO> getAllParentsDto();

    void createParent(Long userId) throws ParentNotFoundException;
    void deleteParent(Long parentId) throws ParentNotFoundException;
}
