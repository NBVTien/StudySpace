package com.example.studyspace.domain.user;

import com.example.studyspace.domain.common.models.Entity;
import com.example.studyspace.domain.common.models.EntityId;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Represents a user in the system.
 * This class extends the <code>Entity</code> class to inherit common entity properties.
 *
 * @version 1.0
 */
@Getter
public class AppUser extends Entity {

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

    protected AppUser(EntityId id,
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
     * @param username  The username of the user.
     * @param password  The password of the user.
     * @param email     The email of the user.
     * @param fullName  The full name of the user.
     * @return A new User object.
     */
    public static AppUser create(String username,
                                 String password,
                                 String email,
                                 String fullName) {
        return new AppUser(EntityId.generate(),
                username,
                password,
                email,
                fullName,
                LocalDateTime.now(),
                LocalDateTime.now());
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
