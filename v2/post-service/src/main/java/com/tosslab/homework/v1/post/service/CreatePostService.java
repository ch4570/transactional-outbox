package com.tosslab.homework.v1.post.service;

import com.tosslab.homework.v1.post.domain.Post;
import com.tosslab.homework.v1.post.repository.PostRepository;
import com.tosslab.homework.v1.post.service.usecase.CreatePostUseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CreatePostService implements CreatePostUseCase {

    private final PostRepository postRepository;
    private final CreatePostEventOutboxService createPostEventOutboxService;

    @Override
    public void create(Long authorId, String title, String content) {
        final Post post = Post.of(authorId, title, content);
        postRepository.save(post);

        createPostEventOutboxService.create(post.getPostId(), authorId, title);
    }
}
