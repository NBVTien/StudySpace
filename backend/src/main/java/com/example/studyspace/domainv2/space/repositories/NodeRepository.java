package com.example.studyspace.domainv2.space.repositories;

import com.example.studyspace.domainv2.content.valueobjects.ContentId;
import com.example.studyspace.domainv2.space.entities.Node;

import java.util.List;

/**
 * Repository interface for managing Node entities in the domain.
 * Provides methods to save, find, and delete nodes by their content ID or space ID.
 */
public interface NodeRepository {

    /**
     * Saves a Node entity to the repository.
     *
     * @param node The Node entity to save.
     */
    void insert(Node node);

    /**
     * Updates an existing Node entity in the repository.
     *
     * @param node The Node entity to update.
     */
    void update(Node node);

    /**
     * Finds a Node entity by its content ID.
     *
     * @param contentId The content ID of the Node to find.
     * @return The Node entity if found, or null if not found.
     */
    Node findById(ContentId contentId);

    /**
     * Deletes a Node entity by its content ID.
     *
     * @param contentId The content ID of the Node to delete.
     */
    void deleteById(ContentId contentId);

    /**
     * Finds all Node entities associated with a specific space ID.
     *
     * @param spaceId The ID of the space to find nodes for.
     * @return A list of Node entities associated with the specified space ID.
     */
    List<Node> findBySpaceId(String spaceId);
}
