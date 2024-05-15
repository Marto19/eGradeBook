package com.egradebook.eGradeBook.services;


import com.egradebook.eGradeBook.entities.Authorities;

/**
 * This interface is used to define the methods that will be used to interact with the Authorities entity.
 */
public interface AuthoritiesService {
    /**
     * This method is used to create a new authority.
     * @param authority The authority object that will be created.
     * @return The authority object that was created.
     */
    public Authorities createAuthority(Authorities authority);

    /**
     * This method is used to update an authority.
     * @param authority The authority object that will be updated.
     * @return The authority object that was updated.
     */
    public void updateAuthority(Authorities authority);

    /**
     * This method is used to delete an authority.
     * @param id The id of the authority that will be deleted.
     */
    public void deleteAuthority(long id);
}
