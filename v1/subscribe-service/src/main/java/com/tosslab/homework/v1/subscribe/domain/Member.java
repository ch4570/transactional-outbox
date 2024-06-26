package com.tosslab.homework.v1.subscribe.domain;


import com.tosslab.homework.v1.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", columnDefinition = "BIGINT")
    private Long memberId;

    @Column(name = "email", columnDefinition = "VARCHAR(50)", nullable = false)
    private String email;

    @Column(name = "nickname", columnDefinition = "VARCHAR(30)", nullable = false)
    private String nickname;

    @Column(name = "webhook_url", columnDefinition = "VARCHAR(200)", nullable = false)
    private String webhookUrl;

    @Builder
    private Member(String email, String nickname, String webhookUrl) {
        this.email = email;
        this.nickname = nickname;
        this.webhookUrl = webhookUrl;
    }

    public static Member of(String email, String nickname, String webhookUrl) {
        return Member.builder()
                .email(email)
                .nickname(nickname)
                .webhookUrl(webhookUrl)
                .build();
    }
}
