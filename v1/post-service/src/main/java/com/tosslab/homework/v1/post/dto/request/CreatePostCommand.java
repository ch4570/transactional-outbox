package com.tosslab.homework.v1.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreatePostCommand {

    @NotNull(message = "작성자 ID는 비어있을 수 없습니다.")
    @Positive(message = "작성자 ID는 음수일 수 없습니다.")
    private final Long authorId;

    @NotBlank(message = "글 제목은 비어있을 수 없습니다.")
    private final String title;

    @NotBlank(message = "글 내용은 비어있을 수 없습니다.")
    private final String content;
}
