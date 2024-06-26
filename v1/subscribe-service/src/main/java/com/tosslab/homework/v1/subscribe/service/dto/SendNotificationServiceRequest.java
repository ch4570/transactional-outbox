package com.tosslab.homework.v1.subscribe.service.dto;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.UUID;

@Builder(access = AccessLevel.PRIVATE)
public record SendNotificationServiceRequest(UUID eventId, Long postId, Long authorId, String title) {


    public static SendNotificationServiceRequest of(UUID eventId, Long postId, Long authorId, String title) {
        return SendNotificationServiceRequest.builder()
                .eventId(eventId)
                .postId(postId)
                .authorId(authorId)
                .title(title)
                .build();
    }
}
