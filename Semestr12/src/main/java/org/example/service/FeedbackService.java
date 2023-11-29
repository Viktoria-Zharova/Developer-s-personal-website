package org.example.service;

import jakarta.servlet.http.HttpServletRequest;
import org.example.model.FeedbackEntity;

import java.util.List;

public interface FeedbackService {

    void createFeedback(HttpServletRequest request);

    void deleteFeedback(HttpServletRequest request);

    List<FeedbackEntity> getAllFeedback();

    List<FeedbackEntity> getFeedbackByUserId(HttpServletRequest request);

    FeedbackEntity getFeedbackById(HttpServletRequest request);

    FeedbackEntity update(HttpServletRequest request);
}
