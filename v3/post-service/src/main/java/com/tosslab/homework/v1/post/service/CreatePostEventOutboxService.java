package com.tosslab.homework.v1.post.service;

import com.tosslab.homework.v1.post.domain.PostEventOutbox;
import com.tosslab.homework.v1.post.repository.PostEventOutboxRepository;
import com.tosslab.homework.v1.post.service.usecase.CreatePostEventOutboxUseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CreatePostEventOutboxService implements CreatePostEventOutboxUseCase {

    private final PostEventOutboxRepository postEventOutboxRepository;

    @Override
    public void create(Long postId, Long authorId, String title) {
        final PostEventOutbox postEventOutbox = PostEventOutbox.of(UUID.randomUUID(), postId, authorId, title);
        postEventOutboxRepository.save(postEventOutbox);
    }
}
