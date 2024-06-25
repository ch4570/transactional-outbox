package com.tosslab.homework.v1.post.repository;

import com.tosslab.homework.v1.post.domain.PostEventOutbox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostEventOutboxRepository extends JpaRepository<PostEventOutbox, Long> {
}
