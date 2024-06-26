package com.tosslab.homework.v1.post.event.object;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.UUID;

@Builder(access = AccessLevel.PRIVATE)
public record CreatePostEvent(UUID eventId, Long postId, Long authorId) {

    public static CreatePostEvent of(UUID eventId, Long postId, Long authorId) {
        return CreatePostEvent.builder()
                .eventId(eventId)
                .postId(postId)
                .authorId(authorId)
                .build();
    }
}
