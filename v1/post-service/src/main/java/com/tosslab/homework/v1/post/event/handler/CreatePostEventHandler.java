package com.tosslab.homework.v1.post.event.handler;

import com.tosslab.homework.v1.post.event.object.CreatePostEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class CreatePostEventHandler {

    private final KafkaTemplate<String, CreatePostEvent> kafkaTemplate;

    @Async
    @TransactionalEventListener
    public void handleEvent(CreatePostEvent event) {
        kafkaTemplate.send("post-create", event);
    }
}
