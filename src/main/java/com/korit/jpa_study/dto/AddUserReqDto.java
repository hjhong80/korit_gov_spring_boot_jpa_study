package com.korit.jpa_study.dto;

import com.korit.jpa_study.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AddUserReqDto {
    private String username;
    private String password;
    private String email;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .createDt(LocalDateTime.now())
                .build();
    }
}
