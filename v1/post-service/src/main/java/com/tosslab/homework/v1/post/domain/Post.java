package com.tosslab.homework.v1.post.domain;

import com.tosslab.homework.v1.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "POST")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", columnDefinition = "BIGINT")
    private Long postId;

    @Column(name = "member_id", columnDefinition = "BIGINT", nullable = false)
    private Long memberId;

    @Column(name = "title", columnDefinition = "VARCHAR(200)", nullable = false)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Builder
    private Post(Long memberId, String title, String content) {}

    public static Post of(Long memberId, String title, String content) {
        return Post.builder()
                .memberId(memberId)
                .title(title)
                .content(content)
                .build();
    }
}
