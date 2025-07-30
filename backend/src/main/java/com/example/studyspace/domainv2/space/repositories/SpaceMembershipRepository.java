package com.example.studyspace.domainv2.space.repositories;

import com.example.studyspace.domain.common.models.EntityId;
import com.example.studyspace.domainv2.space.entities.SpaceMembership;
import com.example.studyspace.domainv2.space.valueobjects.SpaceId;

/**
 * Repository interface for managing SpaceMembership entities in the domain.
 * Provides methods to find, save, and delete space memberships.
 */
public interface SpaceMembershipRepository {

    /**
     * Finds a SpaceMembership by user ID and space ID.
     *
     * @param userId  The ID of the user.
     * @param spaceId The ID of the space.
     * @return The SpaceMembership if found, or null if not found.
     */
    SpaceMembership findById(EntityId userId, EntityId spaceId);

    /**
     * Saves a SpaceMembership to the repository.
     *
     * @param spaceMembership The SpaceMembership entity to save.
     */
    void insert(SpaceMembership spaceMembership);

    /**
     * Updates an existing SpaceMembership in the repository.
     *
     * @param spaceId The ID of the space to which the membership belongs.
     * @param spaceMembership The SpaceMembership entity to update.
     */
    void update(SpaceId spaceId, SpaceMembership spaceMembership);

    /**
     * Deletes a SpaceMembership from the repository.
     *
     * @param spaceMembership The SpaceMembership entity to delete.
     */
    void delete(SpaceMembership spaceMembership);
}