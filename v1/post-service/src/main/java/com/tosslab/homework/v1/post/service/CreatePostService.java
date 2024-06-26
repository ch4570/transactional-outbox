package com.tosslab.homework.v1.post.service;

import com.tosslab.homework.v1.post.domain.Post;
import com.tosslab.homework.v1.post.event.object.CreatePostEvent;
import com.tosslab.homework.v1.post.repository.PostRepository;
import com.tosslab.homework.v1.post.service.usecase.CreatePostUseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CreatePostService implements CreatePostUseCase {

    private final PostRepository postRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void create(Long authorId, String title, String content) {
        final Post post = Post.of(authorId, title, content);
        postRepository.save(post);

        // 게시글 생성 이벤트 발송
        final CreatePostEvent event = CreatePostEvent.of(UUID.randomUUID(), post.getPostId(), authorId, post.getTitle());
        eventPublisher.publishEvent(event);
    }
}
