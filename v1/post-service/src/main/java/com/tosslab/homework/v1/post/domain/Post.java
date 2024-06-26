package com.tosslab.homework.v1.post.domain;

import com.tosslab.homework.v1.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "POST")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", columnDefinition = "BIGINT")
    private Long postId;

    @Column(name = "author_id", columnDefinition = "BIGINT", nullable = false)
    private Long authorId;

    @Column(name = "title", columnDefinition = "VARCHAR(200)", nullable = false)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Builder
    private Post(Long authorId, String title, String content) {
        this.authorId = authorId;
        this.title = title;
        this.content = content;
    }

    public static Post of(Long authorId, String title, String content) {
        return Post.builder()
                .authorId(authorId)
                .title(title)
                .content(content)
                .build();
    }
}
