package com.example.studyspace.infrastructure.repositories;

import com.example.studyspace.application.common.interfaces.repositories.UserRepository;
import com.example.studyspace.domain.user.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private List<User> users = new ArrayList<>(List.of(
        User.create(
            "admin",
            new BCryptPasswordEncoder().encode("123"),
            "admin@email.com",
            "Admin"
        ),
        User.create(
            "user",
            new BCryptPasswordEncoder().encode("123"),
            "user@email.com",
            "User"
        )
    ));

    @Override
    public User getById(UUID id) {
        return users.stream()
            .filter(user -> user.getId().getValue().equals(id))
            .findFirst()
            .orElse(null);
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public void update(UUID id, User user) {
        User existingUser = getById(id);
        users.remove(existingUser);
        users.add(user);
    }

    @Override
    public void delete(UUID id) {
        User user = getById(id);
        if (user != null) {
            users.remove(user);
        }
    }

    @Override
    public User getByUsername(String username) {
        return users.stream()
            .filter(user -> user.getUsername().equals(username))
            .findFirst()
            .orElse(null);
    }
}
