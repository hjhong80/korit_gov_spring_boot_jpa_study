package com.korit.jpa_study.repository;

import com.korit.jpa_study.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Optional<Post> findByTitle(String title);
    Optional<List<Post>> findAllByUserId(Integer userId);
}
