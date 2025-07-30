package com.example.studyspace.domainv2.space.valueobjects;

import java.util.UUID;

/**
 * Represents a unique identifier for a space membership.
 * This class encapsulates a UUID to ensure type safety and clarity in the domain model.
 *
 * @param value the UUID representing the space membership identifier
 */
public record SpaceMembershipId(UUID value) {}
