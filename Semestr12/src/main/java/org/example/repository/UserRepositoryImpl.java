package org.example.repository;

import org.example.mappers.RowMapper;
import org.example.model.UserEntity;
import org.example.model.enums.UserRole;
import org.example.repository.database.PostgresConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private UserRepositoryImpl(){}

    private static class SingletonHolder{
        public static final UserRepository INSTANCE = new UserRepositoryImpl();
    }

    public static UserRepository getInstance(){
        return SingletonHolder.INSTANCE;
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from users where id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(USER_ENTITY_ROW_MAPPER.mapRow(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public void create(UserEntity user) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users (email, first_name, second_name, hash_password, role) VALUES " +
                    "(?,?,?,?,?)");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getSecondName());
            preparedStatement.setString(4, user.getHashPassword());
            preparedStatement.setString(5, UserRole.USER.toString());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long userId) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("delete from users where id = ?");
            statement.setLong(1,userId);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserEntity update(UserEntity userToUpdate) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("update users set email = ?, first_name = ?, second_name = ?, hash_password = ? where id = ?;");
            preparedStatement.setString(1, userToUpdate.getEmail());
            preparedStatement.setString(2, userToUpdate.getFirstName());
            preparedStatement.setString(3, userToUpdate.getSecondName());
            preparedStatement.setString(4, userToUpdate.getHashPassword());
            preparedStatement.setLong(5, userToUpdate.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findById(userToUpdate.getId()).get();
    }

    @Override
    public Optional<UserEntity> findUserByEmail(String email) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from users where email = ?");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(USER_ENTITY_ROW_MAPPER.mapRow(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }


    private static final RowMapper<UserEntity> USER_ENTITY_ROW_MAPPER = resultSet -> {
        try {
            return UserEntity.builder()
                    .id(resultSet.getLong("id"))
                    .email(resultSet.getString("email"))
                    .role(UserRole.valueOf(resultSet.getString("role").toUpperCase()))
                    .hashPassword(resultSet.getString("hash_password"))
                    .firstName(resultSet.getString("first_name"))
                    .secondName(resultSet.getString("second_name"))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    };
}
