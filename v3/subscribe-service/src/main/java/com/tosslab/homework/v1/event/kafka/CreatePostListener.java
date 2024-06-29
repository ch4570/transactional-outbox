package com.tosslab.homework.v1.event.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tosslab.homework.v1.event.object.CreatePostEvent;
import com.tosslab.homework.v1.subscribe.service.dto.SendNotificationServiceRequest;
import com.tosslab.homework.v1.subscribe.service.usecase.SendNotificationUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreatePostListener {

    private final ObjectMapper objectMapper;
    private final SendNotificationUseCase sendNotificationUseCase;

    @KafkaListener(topics = "post-create=event.post_service_db.post_event_outbox", groupId = "post-group", containerFactory = "concurrentKafkaListenerContainerFactory")
    void consumeEvent(@Payload String event) {
        CreatePostEvent createPostEvent = convertFromJson(event);
        log.info("Create Post Event 수신 Request Data = [{}], 수신 날짜 = [{}]", createPostEvent, LocalDateTime.now());

        final SendNotificationServiceRequest serviceRequest = SendNotificationServiceRequest.of(createPostEvent.eventId(), createPostEvent.postId(),createPostEvent.authorId(), createPostEvent.title());
        sendNotificationUseCase.sendNotification(serviceRequest);
    }

    private CreatePostEvent convertFromJson(String eventJson)  {
        try {
            JsonNode extractedNode = objectMapper.readTree(eventJson)
                    .get("payload")
                    .get("after");

            log.error("수신 데이터 = [{}]", extractedNode.toPrettyString());

            return objectMapper.treeToValue(extractedNode, CreatePostEvent.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
