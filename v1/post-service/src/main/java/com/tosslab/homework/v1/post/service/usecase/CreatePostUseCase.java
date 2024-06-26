package com.tosslab.homework.v1.post.service.usecase;

public interface CreatePostUseCase {

    void create(Long memberId, String title, String content);
}
