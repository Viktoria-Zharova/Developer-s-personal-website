package org.example.service;


import jakarta.servlet.http.HttpServletRequest;
import org.example.model.UserEntity;
import org.example.model.enums.UserRole;
import org.example.repository.UserRepository;
import org.example.repository.UserRepositoryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserServiceImpl implements UserService {

    private UserServiceImpl() {
    }

    private static class SingletonHolder {
        public static final UserService INSTANCE = new UserServiceImpl();
    }

    public static UserService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private final UserRepository repository = UserRepositoryImpl.getInstance();

    public final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public void createUser(UserEntity user) {
        repository.create(user);
    }

    @Override
    public void register(HttpServletRequest request) {
        UserEntity user = UserEntity.builder()
                .email(request.getParameter("email"))
                .firstName(request.getParameter("first_name"))
                .secondName(request.getParameter("second_name"))
                .hashPassword(encoder.encode(request.getParameter("password")))
                .role(UserRole.USER)
                .build();
        createUser(user);
        login(request);
    }

    @Override
    public boolean login(HttpServletRequest request) {
        try {
            UserEntity user = repository.findUserByEmail(request.getParameter("email")).orElseThrow(RuntimeException::new);
            if (encoder.matches(request.getParameter("password"), user.getHashPassword())) {
                request.getSession().setAttribute("user", user);
                return true;
            }
            return false;
        } catch (RuntimeException e) {
            return false;
        }
    }

    @Override
    public UserEntity updateUser(HttpServletRequest request) {
        UserEntity userOld = getUserById(((UserEntity)(request.getSession().getAttribute("user"))).getId());
        UserEntity userToUpdate = UserEntity.builder()
                .id(userOld.getId())
                .email(request.getParameter("email"))
                .firstName(request.getParameter("first_name"))
                .secondName(request.getParameter("second_name"))
                .hashPassword(encoder.encode(request.getParameter("password")))
                .role(userOld.getRole())
                .build();
        UserEntity entity = repository.update(userToUpdate);
        login(request);
        return entity;
    }

    @Override
    public void deleteUser(HttpServletRequest request) {
        repository.delete((Long.valueOf(request.getParameter("user_id"))));
    }

    @Override
    public UserEntity findUser(HttpServletRequest request) {
        return getUserById(Long.valueOf(request.getParameter("user_id")));
    }

    private UserEntity getUserById(Long id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }
}
