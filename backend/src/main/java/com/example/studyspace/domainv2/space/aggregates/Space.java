package com.example.studyspace.domainv2.space.aggregates;

import com.example.studyspace.domainv2.content.valueobjects.ContentId;
import com.example.studyspace.domainv2.shared.models.AggregateRoot;
import com.example.studyspace.domainv2.space.entities.SpaceMembership;
import com.example.studyspace.domainv2.space.valueobjects.MembershipRole;
import com.example.studyspace.domainv2.space.valueobjects.SpaceId;
import com.example.studyspace.domainv2.space.valueobjects.SpaceMembershipId;
import com.example.studyspace.domainv2.user.valueobjects.UserId;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a Space aggregate in the domain model.
 * A Space is a place containing nodes of content for many users to collaborate on.
 */
@Getter
public class Space extends AggregateRoot<SpaceId> {
    private String name;
    private String description;
    private final UserId ownerId;
    private final List<SpaceMembership> memberships;
    private final List<ContentId> nodes;

    protected Space(
        SpaceId spaceId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String name,
        String description,
        UserId ownerId,
        List<SpaceMembership> memberships,
        List<ContentId> nodes
    ) {
        super(spaceId, createdAt, updatedAt);
        this.name = name;
        this.description = description;
        this.ownerId = ownerId;
        this.memberships = memberships;
        this.nodes = nodes;
    }

    public static Space create(
        SpaceId spaceId,
        String name,
        String description,
        UserId ownerId
    ) {
        return new Space(
            spaceId,
            LocalDateTime.now(),
            LocalDateTime.now(),
            name,
            description,
            ownerId,
            List.of(),
            List.of()
        );
    }

    public void update(String name, String description) {
        this.name = name;
        this.description = description;
        this.update();
    }

    public SpaceMembership addMember(
        SpaceMembershipId spaceMembershipId,
        UserId userId
    ) {
        SpaceMembership membership = SpaceMembership.create(
            spaceMembershipId,
            this.getId(),
            userId,
            MembershipRole.MEMBER
        );
        memberships.add(membership);
        return membership;
    }

    public SpaceMembership addEditor(
        SpaceMembershipId spaceMembershipId,
        UserId userId
    ) {
        SpaceMembership membership = SpaceMembership.create(
            spaceMembershipId,
            this.getId(),
            userId,
            MembershipRole.EDITOR
        );
        memberships.add(membership);
        return membership;
    }

    public SpaceMembership addGuest(
        SpaceMembershipId spaceMembershipId,
        UserId userId
    ) {
        SpaceMembership membership = SpaceMembership.create(
            spaceMembershipId,
            this.getId(),
            userId,
            MembershipRole.GUEST
        );
        memberships.add(membership);
        return membership;
    }

    public void removeMember(UserId userId) {
        memberships.removeIf(membership -> membership.getUserId().equals(userId));
    }
}