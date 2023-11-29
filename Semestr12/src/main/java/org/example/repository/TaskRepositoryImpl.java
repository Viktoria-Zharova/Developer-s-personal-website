package org.example.repository;

import org.example.mappers.RowMapper;
import org.example.model.TaskEntity;
import org.example.model.UserEntity;
import org.example.model.enums.UserRole;
import org.example.repository.database.PostgresConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryImpl implements TaskRepository{

    private TaskRepositoryImpl(){}

    private static class SingletonHolder{
        public static final TaskRepository INSTANCE = new TaskRepositoryImpl();
    }

    public static TaskRepository getInstance(){
        return SingletonHolder.INSTANCE;
    }

    private static final RowMapper<TaskEntity> TASK_ENTITY_ROW_MAPPER = resultSet -> {
        try {
            return TaskEntity.builder()
                    .id(resultSet.getLong("id"))
                    .description(resultSet.getString("description"))
                    .userId(resultSet.getLong("user_id"))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    };

    @Override
    public void create(TaskEntity entity) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("insert into task (description, user_id) values (?, ?);");
            statement.setString(1,entity.getDescription());
            statement.setLong(2,entity.getUserId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(TaskEntity entity) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("update task set description = ? where id = ?;");
            statement.setString(1,entity.getDescription());
            statement.setLong(2, entity.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TaskEntity> findAll() {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from task;");
            ResultSet set = statement.executeQuery();
            ArrayList<TaskEntity> list = new ArrayList<>();
            while (set.next()){
                list.add(TASK_ENTITY_ROW_MAPPER.mapRow(set));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long taskId) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("delete from users where id =?;");
            statement.setLong(1, taskId);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TaskEntity> findAllByUserId(Long userId) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from task where user_id = ?;");
            statement.setLong(1,userId);
            ResultSet set = statement.executeQuery();
            ArrayList<TaskEntity> list = new ArrayList<>();
            while (set.next()){
                list.add(TASK_ENTITY_ROW_MAPPER.mapRow(set));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
