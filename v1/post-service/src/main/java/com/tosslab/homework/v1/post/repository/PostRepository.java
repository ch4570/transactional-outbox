package com.tosslab.homework.v1.post.repository;

import com.tosslab.homework.v1.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
