package org.example.repository;

import org.example.model.FeedbackEntity;

import java.util.List;

public interface FeedbackRepository {
    void create(FeedbackEntity feedback);

    void delete(Long feedbackId);

    List<FeedbackEntity> findAll();

    List<FeedbackEntity> findByUserId(Long userId);

    FeedbackEntity findById(Long feedbackId);

    void update(FeedbackEntity feedback);
}
