package org.example.service;

import jakarta.servlet.http.HttpServletRequest;
import org.example.model.FeedbackEntity;
import org.example.model.UserEntity;
import org.example.repository.FeedbackRepository;
import org.example.repository.FeedbackRepositoryImpl;
import org.example.repository.UserRepository;
import org.example.repository.UserRepositoryImpl;

import java.util.List;

public class FeedbackServiceImpl implements FeedbackService{

    private FeedbackServiceImpl(){}

    private static class SingletonHolder{
        public static final FeedbackService INSTANCE = new FeedbackServiceImpl();
    }

    public static FeedbackService getInstance(){
        return SingletonHolder.INSTANCE;
    }

    private final FeedbackRepository repository = FeedbackRepositoryImpl.getInstance();

    @Override
    public void createFeedback(HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        FeedbackEntity feedback = FeedbackEntity.builder()
                .author_id(user.getId())
                .description(request.getParameter("description"))
                .build();
        repository.create(feedback);
    }

    @Override
    public void deleteFeedback(HttpServletRequest request) {
        repository.delete(Long.valueOf(request.getParameter("feedback_id")));
    }

    @Override
    public List<FeedbackEntity> getAllFeedback() {
        return repository.findAll();
    }

    @Override
    public List<FeedbackEntity> getFeedbackByUserId(HttpServletRequest request) {
        return repository.findByUserId(Long.valueOf(request.getParameter("user_id")));
    }

    @Override
    public FeedbackEntity getFeedbackById(HttpServletRequest request) {
        return repository.findById(Long.valueOf(request.getParameter("feedback_id")));
    }

    @Override
    public FeedbackEntity update(HttpServletRequest request) {
        FeedbackEntity feedback = FeedbackEntity.builder()
                .id(Long.valueOf(request.getParameter("feedback_id")))
                .description(request.getParameter("description"))
                .build();
        repository.update(feedback);
        return feedback;
    }
}
