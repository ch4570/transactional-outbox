package com.tosslab.homework.v1.post.service;

import com.tosslab.homework.v1.post.domain.PostEventOutbox;
import com.tosslab.homework.v1.post.repository.PostEventOutboxRepository;
import com.tosslab.homework.v1.post.service.usecase.LoadPostEventOutboxUseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LoadPostEventOutboxService implements LoadPostEventOutboxUseCase {

    private final PostEventOutboxRepository postEventOutboxRepository;

    @Override
    public List<PostEventOutbox> loadBeforeProcessingEvents() {
        return postEventOutboxRepository.loadBeforeProcessingEvents();
    }
}
