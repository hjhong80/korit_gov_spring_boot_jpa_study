package com.korit.jpa_study.dto;

import com.korit.jpa_study.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class EditPostReqDto {
    private Integer postId;
    private String title;
    private String content;
    private Integer userId;
    private LocalDateTime createDt;

}
