package org.example.repository;

import org.example.mappers.RowMapper;
import org.example.model.ProjectExampleEntity;
import org.example.repository.database.PostgresConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepositoryImpl implements ProjectRepository {

    private ProjectRepositoryImpl(){}

    private static class SingletonHolder{
        public static final ProjectRepository INSTANCE = new ProjectRepositoryImpl();
    }

    public static ProjectRepository getInstance(){
        return SingletonHolder.INSTANCE;
    }

    private static final RowMapper<ProjectExampleEntity> EXAMPLE_ENTITY_ROW_MAPPER = resultSet -> {
        try {
            return ProjectExampleEntity.builder()
                    .id(resultSet.getLong("id"))
                    .title(resultSet.getString("title"))
                    .description(resultSet.getString("description"))
                    .gitUrl(resultSet.getString("git_url"))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    };

    @Override
    public List<ProjectExampleEntity> findAll() {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from examples;");
            ResultSet set = statement.executeQuery();
            ArrayList<ProjectExampleEntity> list = new ArrayList<>();
            while (set.next()){
                list.add(EXAMPLE_ENTITY_ROW_MAPPER.mapRow(set));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProjectExampleEntity create(ProjectExampleEntity example) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("insert into examples (title, description, git_url) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, example.getTitle());
            statement.setString(2, example.getDescription());
            statement.setString(3, example.getGitUrl());
            statement.execute();
            ResultSet key = statement.getGeneratedKeys();
            key.next();
            example.setId(key.getLong(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return example;
    }

    @Override
    public ProjectExampleEntity findById(Long id) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from examples where id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return EXAMPLE_ENTITY_ROW_MAPPER.mapRow(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void update(ProjectExampleEntity example) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("update examples set description = ?, git_url = ?, title = ? where id = ?;");
            statement.setString(1, example.getDescription());
            statement.setString(2, example.getGitUrl());
            statement.setString(3, example.getTitle());
            statement.setLong(4, example.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long projectId) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("delete from examples where id = ?;");
            statement.setLong(1, projectId);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
