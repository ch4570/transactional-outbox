package com.tosslab.homework.v1.subscribe.domain;

import com.tosslab.homework.v1.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import java.util.UUID;

@Entity
@Getter
@Table(name = "POST_EVENT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostEvent extends BaseTimeEntity implements Persistable<UUID> {

    @Id
    @Column(name = "event_id", columnDefinition = "BINARY(16)")
    private UUID eventId;

    @Column(name = "post_id", columnDefinition = "BIGINT", nullable = false)
    private Long postId;

    @Builder
    private PostEvent(UUID eventId, Long postId) {
        this.eventId = eventId;
        this.postId = postId;
    }

    public static PostEvent of(UUID eventId, Long postId) {
        return PostEvent.builder()
                .eventId(eventId)
                .postId(postId)
                .build();

    }

    @Override
    public UUID getId() {
        return eventId;
    }

    @Override
    public boolean isNew() {
        return super.getCreatedAt() == null;
    }
}
