package com.tosslab.homework.v1.event.object;

import java.util.UUID;

public record CreatePostEvent(UUID eventId, Long postId, Long authorId, String title) {
}
