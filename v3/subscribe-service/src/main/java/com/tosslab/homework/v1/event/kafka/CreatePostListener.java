package com.tosslab.homework.v1.event.kafka;

import com.tosslab.homework.v1.event.object.CreatePostEvent;
import com.tosslab.homework.v1.subscribe.service.dto.SendNotificationServiceRequest;
import com.tosslab.homework.v1.subscribe.service.usecase.SendNotificationUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreatePostListener {

    private final SendNotificationUseCase sendNotificationUseCase;

    @KafkaListener(topics = "post-create-event.post_service_db.post_event_outbox", groupId = "post-group", containerFactory = "concurrentKafkaListenerContainerFactory")
    void consumeEvent(CreatePostEvent event) {
        log.info("Create Post Event 수신 Request Data = [{}], 수신 날짜 = [{}]", event, LocalDateTime.now());

        final SendNotificationServiceRequest serviceRequest = SendNotificationServiceRequest.of(event.eventId(), event.postId(),event.authorId(), event.title());
        sendNotificationUseCase.sendNotification(serviceRequest);
    }
}
