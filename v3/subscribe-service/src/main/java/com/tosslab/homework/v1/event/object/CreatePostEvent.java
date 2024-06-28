package com.tosslab.homework.v1.event.object;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record CreatePostEvent(
        @JsonProperty("event_id")
        UUID eventId,
        @JsonProperty("post_id")
        Long postId,
        @JsonProperty("author_id")
        Long authorId,
        String title) {
}
