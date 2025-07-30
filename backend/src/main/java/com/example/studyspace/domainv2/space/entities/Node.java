package com.example.studyspace.domainv2.space.entities;

import com.example.studyspace.domainv2.content.valueobjects.ContentId;
import com.example.studyspace.domainv2.shared.models.Entity;
import com.example.studyspace.domainv2.space.valueobjects.SpaceId;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Represents a Node in the space domain.
 * A Node contains how to organize content within a space.
 */
@Getter
public class Node extends Entity<ContentId> {

    /**
     * The space to which this node belongs.
     */
    private final SpaceId spaceId;

    /**
     * The order of this node within the space.
     * This is used to determine the position of the node in a list or hierarchy.
     */
    private int order;

    /**
     * Constructor for Node.
     *
     * @param contentId The unique identifier for the content associated with this node.
     * @param createdAt The timestamp when this node was created.
     * @param updatedAt The timestamp when this node was last updated.
     * @param spaceId   The unique identifier for the space to which this node belongs.
     * @param order     The order of this node within the space.
     */
    protected Node(ContentId contentId, LocalDateTime createdAt, LocalDateTime updatedAt, SpaceId spaceId, int order) {
        super(contentId, createdAt, updatedAt);
        this.order = order;
        this.spaceId = spaceId;
    }

    /**
     * Factory method to create a new Node instance.
     *
     * @param contentId The unique identifier for the content associated with this node.
     * @param spaceId   The unique identifier for the space to which this node belongs.
     * @param order     The order of this node within the space.
     * @return A new Node instance.
     */
    public static Node create(ContentId contentId, SpaceId spaceId, int order) {
        return new Node(contentId, LocalDateTime.now(), LocalDateTime.now(), spaceId, order);
    }

    /**
     * Updates the node's order.
     */
    public void reorder(int newOrder) {
        this.order = newOrder;
        this.update();
    }
}
