package com.tosslab.homework.v1.post.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "POST_EVENT_OUTBOX")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostEventOutbox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", columnDefinition = "BIGINT")
    private Long eventId;

    @Column(name = "post_id", columnDefinition = "BIGINT", nullable = false)
    private Long postId;

    @Column(name = "member_id", columnDefinition = "BIGINT", nullable = false)
    private Long memberId;

    @Builder
    private PostEventOutbox(Long postId, Long memberId) {}

    public static PostEventOutbox of(Long postId, Long memberId) {
        return PostEventOutbox.builder()
                .postId(postId)
                .memberId(memberId)
                .build();
    }
}
