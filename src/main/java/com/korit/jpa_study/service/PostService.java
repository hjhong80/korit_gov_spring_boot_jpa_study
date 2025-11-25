package com.korit.jpa_study.service;

import com.korit.jpa_study.dto.AddPostReqDto;
import com.korit.jpa_study.dto.ApiRespDto;
import com.korit.jpa_study.dto.EditPostReqDto;
import com.korit.jpa_study.entity.Post;
import com.korit.jpa_study.repository.PostRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public ApiRespDto<?> addPost(AddPostReqDto addPostReqDto) {
        Optional<Post> foundPost = postRepository.findByTitle(addPostReqDto.getTitle());
        if(foundPost.isPresent()) {
            return new ApiRespDto<>("success","동일한 제목이 존재합니다.", foundPost.get());
        }
        Post result = postRepository.save(addPostReqDto.toEntity());
        return new ApiRespDto<>("success","추가에 성공하였습니다.", result);
    }

    public ApiRespDto<?> getAll() {
        return new ApiRespDto<>("success", "조회에 성공하였습니다.", postRepository.findAll());
    }

    public ApiRespDto<?> editPost(EditPostReqDto editPostReqDto) {
        Optional<Post> foundPost = postRepository.findById(editPostReqDto.getPostId());
        if(foundPost.isEmpty()) {
            return new ApiRespDto<>("failed", "조회에 실패하였습니다.", editPostReqDto);
        }
        Post post = foundPost.get();
        post.setTitle(editPostReqDto.getTitle());
        post.setContent(editPostReqDto.getContent());
        post.setUpdateDt(LocalDateTime.now());
        return new ApiRespDto<>("success", "수정에 성공하였습니다.", postRepository.save(post));
    }

    public ApiRespDto<?> removePost(Integer postId) {
        Optional<Post> foundPost = postRepository.findById(postId);
        if (foundPost.isEmpty()) {
            return new ApiRespDto<>("failed", "조회에 실패하였습니다.", postId);
        }
        postRepository.deleteById(postId);
        return new ApiRespDto<>("success", "삭제에 성공하였습니다.",postId);
    }

    public ApiRespDto<?> getPostByPostId(Integer postId) {
        Optional<Post> foundPost = postRepository.findById(postId);
        if (foundPost.isEmpty()) {
            return new ApiRespDto<>("failed", "조회에 실패하였습니다.", postId);
        }
        return new ApiRespDto<>("success", "조회에 성공하였습니다.",foundPost.get());
    }

}
