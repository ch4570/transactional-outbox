package com.tosslab.homework.v1.post.domain;

import com.tosslab.homework.v1.common.domain.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.domain.Persistable;

import java.util.UUID;

@Entity
@Getter
@Builder
@Table(name = "POST_EVENT_OUTBOX")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostEventOutbox extends BaseTimeEntity implements Persistable<UUID> {

    @Id
    @Column(name = "event_id", columnDefinition = "BINARY(16)")
    private UUID eventId;

    @Column(name = "post_id", columnDefinition = "BIGINT", nullable = false)
    private Long postId;

    @Column(name = "author_id", columnDefinition = "BIGINT", nullable = false)
    private Long authorId;

    @Column(name = "title", columnDefinition = "TEXT", nullable = false)
    private String title;

    @Column(name = "send_status", columnDefinition = "BOOLEAN")
    private boolean sendStatus;

    @Override
    public UUID getId() {
        return eventId;
    }

    @Override
    public boolean isNew() {
        return super.getCreatedAt() == null;
    }

    public void completeEvent() {
        this.sendStatus = true;
    }

    public static PostEventOutbox of(UUID eventId, Long postId, Long authorId, String title) {
        return PostEventOutbox.builder()
                .eventId(eventId)
                .postId(postId)
                .authorId(authorId)
                .title(title)
                .sendStatus(false)
                .build();
    }
}
