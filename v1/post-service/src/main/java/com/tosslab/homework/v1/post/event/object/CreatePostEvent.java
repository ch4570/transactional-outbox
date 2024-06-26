package com.tosslab.homework.v1.post.event.object;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.UUID;

@Builder(access = AccessLevel.PRIVATE)
public record CreatePostEvent(UUID eventId, Long postId, Long authorId, String title) {

    public static CreatePostEvent of(UUID eventId, Long postId, Long authorId, String title) {
        return CreatePostEvent.builder()
                .eventId(eventId)
                .postId(postId)
                .authorId(authorId)
                .title(title)
                .build();
    }
}
