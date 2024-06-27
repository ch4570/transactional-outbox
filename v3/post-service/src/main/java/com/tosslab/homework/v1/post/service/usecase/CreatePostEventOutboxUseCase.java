package com.tosslab.homework.v1.post.service.usecase;

public interface CreatePostEventOutboxUseCase {

    void create(Long postId, Long authorId, String title);
}
