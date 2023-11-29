package org.example.model;

import lombok.*;
import org.example.model.enums.UserRole;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    Long id;
    String email;
    String firstName;
    String secondName;
    String hashPassword;
    UserRole role;
}
