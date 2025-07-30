package com.example.studyspace.domainv2.user.aggregates;

import com.example.studyspace.domainv2.shared.models.Entity;
import com.example.studyspace.domainv2.user.valueobjects.UserId;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Represents a user in the system.
 * This class extends the <code>Entity</code> class to inherit common entity properties.
 *
 * @version 1.0
 */
@Getter
public class User extends Entity<UserId> {

    /**
     * The username of the user.
     */
    private String username;

    /**
     * The password of the user.
     */
    private String password;

    /**
     * The email of the user.
     */
    private String email;

    /**
     * The full name of the user.
     */
    private String fullName;

    protected User(UserId id,
                   String username,
                   String password,
                   String email,
                   String fullName,
                   LocalDateTime createdAt,
                   LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
    }

    /**
     * Factory method to create a new User object.
     *
     * @param id        The unique identifier for the user.
     * @param username  The username of the user.
     * @param password  The password of the user.
     * @param email     The email of the user.
     * @param fullName  The full name of the user.
     * @return A new User object.
     */
    public static User create(UserId id,
                              String username,
                              String password,
                              String email,
                              String fullName) {
        return new User(
            id,
            username,
            password,
            email,
            fullName,
            LocalDateTime.now(),
            LocalDateTime.now()
        );
    }

    /**
     * This method updates the user's information and sets the updated timestamp to the current time.
     *
     * @param username  The new username of the user.
     * @param password  The new password of the user.
     * @param email     The new email of the user.
     * @param fullName  The new full name of the user.
     */
    public void update(String username,
                       String password,
                       String email,
                       String fullName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        super.update();
    }
}
