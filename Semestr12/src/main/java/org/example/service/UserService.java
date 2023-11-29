package org.example.service;

import jakarta.servlet.http.HttpServletRequest;
import org.example.model.UserEntity;

public interface UserService {

    public void createUser(UserEntity user);

    public void register(HttpServletRequest request);

    public boolean login (HttpServletRequest request);

    public UserEntity updateUser(HttpServletRequest request);

    public void deleteUser(HttpServletRequest request);

    public UserEntity findUser(HttpServletRequest request);
}
