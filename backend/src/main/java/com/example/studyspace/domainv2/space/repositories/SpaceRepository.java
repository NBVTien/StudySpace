package com.example.studyspace.domainv2.space.repositories;

import com.example.studyspace.domainv2.shared.models.Collection;
import com.example.studyspace.domainv2.space.aggregates.Space;
import com.example.studyspace.domainv2.space.valueobjects.SpaceId;

/**
 * Repository interface for managing Space entities in the domain.
 * Provides methods to find, insert, update, and delete spaces.
 */
public interface SpaceRepository {

    /**
     * Finds all spaces in the repository.
     *
     * @return A collection of all Space entities.
     */
    Collection<Space> findAll();

    /**
     * Finds a Space by its unique identifier.
     *
     * @param spaceId The ID of the space to find.
     * @return The Space entity if found, or null if not found.
     */
    Space findById(SpaceId spaceId);

    /**
     * Inserts a new Space into the repository.
     *
     * @param space The Space entity to insert.
     */
    void insert(Space space);

    /**
     * Updates an existing Space in the repository.
     *
     * @param space The Space entity to update.
     */
    void update(Space space);

    /**
     * Deletes a Space from the repository.
     *
     * @param space The Space entity to delete.
     */
    void delete(Space space);

    /**
     * Finds all spaces owned by a specific user.
     *
     * @param ownerId The ID of the user who owns the spaces.
     * @return A collection of Space entities owned by the specified user.
     */
    Collection<Space> findByOwnerId(String ownerId);
}