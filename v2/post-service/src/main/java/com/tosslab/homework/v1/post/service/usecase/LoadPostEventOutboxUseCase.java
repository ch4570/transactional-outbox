package com.tosslab.homework.v1.post.service.usecase;

import com.tosslab.homework.v1.post.domain.PostEventOutbox;

import java.util.List;

public interface LoadPostEventOutboxUseCase {

    List<PostEventOutbox> loadBeforeProcessingEvents();
}
