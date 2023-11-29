package org.example.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackEntity {
    Long id;
//    Integer score;
//    String title;
    String description;
    String author;
    Long author_id;

}
