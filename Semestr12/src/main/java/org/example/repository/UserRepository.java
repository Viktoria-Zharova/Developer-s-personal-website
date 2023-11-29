package org.example.repository;

import org.example.model.UserEntity;

import java.util.Optional;

public interface UserRepository {
    Optional<UserEntity> findById(Long id);

    void create(UserEntity user);

    void delete(Long userId);

    UserEntity update(UserEntity userToUpdate);

    Optional<UserEntity> findUserByEmail(String email);
}
