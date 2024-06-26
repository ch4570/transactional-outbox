package com.tosslab.homework.v1.post.event.object;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class CreatePostEvent {

    private final Long postId;
    private final Long authorId;

    public static CreatePostEvent of(Long postId, Long authorId) {
        return CreatePostEvent.builder()
                .postId(postId)
                .authorId(authorId)
                .build();
    }
}
