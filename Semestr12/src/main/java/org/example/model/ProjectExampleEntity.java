package org.example.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectExampleEntity {
    Long id;
    String title;
    String description;
    String gitUrl;
}
