package org.example.model;

import lombok.*;
import org.example.model.enums.TaskStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity {
    Long id;

    Long userId;
//    String title;
    String description;
//    LocalDateTime deadline;
//    Long payment;
//    TaskStatus status;
}
