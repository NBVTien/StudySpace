package com.example.studyspace.domainv2.user.repositories;

import com.example.studyspace.domainv2.user.aggregates.User;
import com.example.studyspace.domainv2.user.valueobjects.UserId;

import java.util.Optional;

/**
 * Repository interface for managing User entities.
 * This interface defines methods for retrieving, saving, and deleting User entities.
 * It abstracts the underlying data storage mechanism, allowing for flexibility in implementation.
 *
 * @version 1.0
 */
public interface UserRepository {

    /**
     * Finds a User by its unique identifier.
     *
     * @param userId The unique identifier of the User.
     * @return An Optional containing the User if found, or empty if not found.
     */
    Optional<User> findById(UserId userId);

    /**
     * Finds a User by its username.
     *
     * @param username The username of the User.
     * @return An Optional containing the User if found, or empty if not found.
     */
    Optional<User> findByUsername(String username);

    /**
     * Finds a User by its email address.
     *
     * @param email The email address of the User.
     * @return An Optional containing the User if found, or empty if not found.
     */
    Optional<User> findByEmail(String email);

    /**
     * Saves a User entity to the repository.
     *
     * @param user The User entity to be saved.
     */
    void save(User user);

    /**
     * Deletes a User entity from the repository.
     *
     * @param user The User entity to be deleted.
     */
    void delete(User user);

    /**
     * Generates a new unique identifier for a User.
     *
     * @return A new UserId.
     */
    UserId nextIdentity();
}
