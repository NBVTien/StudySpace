package com.example.studyspace.domainv2.space.entities;

import com.example.studyspace.domainv2.shared.models.Entity;
import com.example.studyspace.domainv2.space.valueobjects.MembershipRole;
import com.example.studyspace.domainv2.space.valueobjects.SpaceId;
import com.example.studyspace.domainv2.space.valueobjects.SpaceMembershipId;
import com.example.studyspace.domainv2.user.valueobjects.UserId;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Represents a membership in a space, including the user and their access types.
 */
@Getter
public class SpaceMembership extends Entity<SpaceMembershipId> {

    /**
     * The ID of the space this membership belongs to.
     */
    private final SpaceId spaceId;

    /**
     * The ID of the user who is a member of the space.
     */
    private final UserId userId;

    /**
     * The role of the user in the space, which defines their access types.
     */
    private MembershipRole membershipRole;

    /**
     * Constructs a new SpaceMembership with the specified parameters.
     *
     * @param id          The unique identifier for this membership.
     * @param createdAt   The date and time when this membership was created.
     * @param updatedAt   The date and time when this membership was last updated.
     * @param spaceId     The ID of the space this membership belongs to.
     * @param userId      The ID of the user who is a member of the space.
     * @param membershipRole The role of the user in the space, defining their access types.
     */
    protected SpaceMembership(
        SpaceMembershipId id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        SpaceId spaceId,
        UserId userId,
        MembershipRole membershipRole)
    {
        super(id, createdAt, updatedAt);
        this.spaceId = spaceId;
        this.userId = userId;
        this.membershipRole = membershipRole;
    }

    /**
     * Creates a new SpaceMembership with the specified parameters.
     *
     * @param id          The unique identifier for this membership.
     * @param spaceId     The ID of the space this membership belongs to.
     * @param userId      The ID of the user who is a member of the space.
     * @param membershipRole The role of the user in the space, defining their access types.
     * @return A new SpaceMembership instance.
     */
    public static SpaceMembership create(
        SpaceMembershipId id,
        SpaceId spaceId,
        UserId userId,
        MembershipRole membershipRole
    ) {
        return new SpaceMembership(
            id,
            LocalDateTime.now(),
            LocalDateTime.now(),
            spaceId,
            userId,
            membershipRole
        );
    }

    /**
     * Updates the membership role of this SpaceMembership.
     *
     * @param newRole The new role to set for this membership.
     */
    public void updateMembershipRole(MembershipRole newRole) {
        this.membershipRole = newRole;
        this.update();
    }
}
