package com.example.studyspace.infrastructure.repositories;

import com.example.studyspace.application.common.interfaces.repositories.UserRepository;
import com.example.studyspace.domain.user.AppUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private List<AppUser> users = new ArrayList<>(List.of(
        AppUser.create(
            "admin",
            new BCryptPasswordEncoder().encode("123"),
            "admin@email.com",
            "Admin"
        ),
        AppUser.create(
            "user",
            new BCryptPasswordEncoder().encode("123"),
            "user@email.com",
            "User"
        )
    ));

    @Override
    public AppUser getById(UUID id) {
        return users.stream()
            .filter(user -> user.getId().getValue().equals(id))
            .findFirst()
            .orElse(null);
    }

    @Override
    public void save(AppUser user) {
        users.add(user);
    }

    @Override
    public void update(UUID id, AppUser user) {
        AppUser existingUser = getById(id);
        users.remove(existingUser);
        users.add(user);
    }

    @Override
    public void delete(UUID id) {
        AppUser user = getById(id);
        if (user != null) {
            users.remove(user);
        }
    }

    @Override
    public AppUser getByUsername(String username) {
        return users.stream()
            .filter(user -> user.getUsername().equals(username))
            .findFirst()
            .orElse(null);
    }
}
