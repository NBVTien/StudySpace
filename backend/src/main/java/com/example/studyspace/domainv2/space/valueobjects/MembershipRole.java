package com.example.studyspace.domainv2.space.valueobjects;

/**
 * Represents the role of a user in a space, defining their access types.
 * This is used to manage permissions and capabilities within a space.
 */
public enum MembershipRole {
    EDITOR, // Full access to manage the space
    MEMBER, // Standard access to participate in the space
    GUEST; // Limited access, typically read-only
}
