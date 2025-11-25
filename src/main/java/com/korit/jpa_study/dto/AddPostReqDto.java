package com.korit.jpa_study.dto;

import com.korit.jpa_study.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddPostReqDto {
    private String title;
    private String content;
    private Integer userId;

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .userId(userId)
                .createDt(LocalDateTime.now())
                .build();
    }
}
