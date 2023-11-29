package org.example.repository;

import org.example.mappers.RowMapper;
import org.example.model.FeedbackEntity;
import org.example.model.UserEntity;
import org.example.repository.database.PostgresConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeedbackRepositoryImpl implements FeedbackRepository{

    private FeedbackRepositoryImpl(){}

    private static class SingletonHolder{
        public static final FeedbackRepository INSTANCE = new FeedbackRepositoryImpl();
    }

    public static FeedbackRepository getInstance(){
        return SingletonHolder.INSTANCE;
    }

    private static final UserRepository repository = UserRepositoryImpl.getInstance();

    private static final RowMapper<FeedbackEntity> FEEDBACK_ENTITY_ROW_MAPPER = resultSet -> {
        try {
            UserEntity user = repository.findById(resultSet.getLong("author_id")).orElseThrow(() -> new Error("error"));
            return FeedbackEntity.builder()
                    .id(resultSet.getLong("id"))
                    .description(resultSet.getString("description"))
                    .author(user.getFirstName()+" "+ user.getSecondName())
                    .author_id(user.getId())
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    };

    @Override
    public void create(FeedbackEntity feedback) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("insert into feedback (description, author_id) values (?,?);");
            statement.setString(1,feedback.getDescription());
            statement.setLong(2, feedback.getAuthor_id());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long feedbackId) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("delete from feedback where id = ?;");
            statement.setLong(1, feedbackId);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FeedbackEntity> findAll() {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from feedback");
            ResultSet set = statement.executeQuery();
            ArrayList<FeedbackEntity> list = new ArrayList<>();
            while (set.next()){
                list.add(FEEDBACK_ENTITY_ROW_MAPPER.mapRow(set));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FeedbackEntity> findByUserId(Long userId) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from feedback left join " +
                    "user_feedback uf on feedback.id = uf.feedback_id where user_id = ?;");
            statement.setLong(1, userId);
            ResultSet set = statement.executeQuery();
            ArrayList<FeedbackEntity> list = new ArrayList<>();
            while (set.next()){
                list.add(FEEDBACK_ENTITY_ROW_MAPPER.mapRow(set));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FeedbackEntity findById(Long feedbackId) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from feedback where id = ?;");
            statement.setLong(1, feedbackId);
            ResultSet set = statement.executeQuery();
            if (set.next()){
                return FEEDBACK_ENTITY_ROW_MAPPER.mapRow(set);
            }
            throw new RuntimeException();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(FeedbackEntity feedback) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("update feedback set description = ? where id = ?;");
            statement.setString(1,feedback.getDescription());
            statement.setLong(2, feedback.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
