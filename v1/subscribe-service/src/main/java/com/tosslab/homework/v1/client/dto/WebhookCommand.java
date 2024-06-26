package com.tosslab.homework.v1.client.dto;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record WebhookCommand(
        String connectColor,
        String body,
        List<ConnectInfo> connectInfo
) {

    public static WebhookCommand of(String title, String authorNickname) {
        final ConnectInfo info = ConnectInfo.builder()
                .title("* 새 글 등록 알람 *")
                .description(String.format("%s님의 새 글 등록 알람이 도착했습니다.", authorNickname))
                .build();

        return WebhookCommand.builder()
                .connectColor("#FEF31B")
                .body(String.format("%s 님이 새 글을 작성했습니다. \n 글 제목 : %s", authorNickname, title))
                .connectInfo(List.of(info))
                .build();
    }

    @Builder(access = AccessLevel.PRIVATE)
    public record ConnectInfo(
            String title,
            String description
    ) {}
}
