package com.example.studyspace.application.common.interfaces.repositories;

import com.example.studyspace.domain.user.AppUser;

import java.util.UUID;

/**
 * <code>UserRepository</code> is an interface that defines the contract for a repository that manages
 * <code>User</code> entities. It provides methods to access and manipulate users in the data store.
 *
 * @version 1.0
 */
public interface UserRepository {

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param id The unique identifier of the user.
     * @return The <code>User</code> object associated with the specified ID.
     *
     */
    AppUser getById(UUID id);

    /**
     * Saves a new user to the data store.
     *
     * @param user The <code>User</code> object to be saved.
     *
     */
    void save(AppUser user);

    /**
     * Updates an existing user in the data store.
     *
     * @param id The unique identifier of the user to be updated.
     * @param user The <code>User</code> object with updated values.
     *
     */
    void update(UUID id, AppUser user);

    /**
     * Deletes a user from the data store.
     *
     * @param id The unique identifier of the user to be deleted.
     *
     */
    void delete(UUID id);

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user.
     * @return The <code>User</code> object associated with the specified username.
     *
     */
    AppUser getByUsername(String username);
}
