package com.tosslab.homework.v1.subscribe.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "SUBSCRIBE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Subscribe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscribe_id", columnDefinition = "BIGINT")
    private Long subscribeId;

    @Column(name = "target_id", columnDefinition = "BIGINT", nullable = false)
    private Long targetId;

    @Column(name = "subscriber_id", columnDefinition = "BIGINT", nullable = false)
    private Long subscriberId;

    @Builder
    private Subscribe(Long targetId, Long subscriberId) {
        this.targetId = targetId; this.subscriberId = subscriberId;
    }

    public static Subscribe of(Long targetId, Long subscriberId) {
        return Subscribe.builder()
                .targetId(targetId)
                .subscriberId(subscriberId)
                .build();
    }
}
