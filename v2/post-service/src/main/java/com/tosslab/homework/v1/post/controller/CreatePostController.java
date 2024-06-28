package com.tosslab.homework.v1.post.controller;

import com.tosslab.homework.v1.common.dto.BaseResponse;
import com.tosslab.homework.v1.post.dto.request.CreatePostCommand;
import com.tosslab.homework.v1.post.service.CreatePostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreatePostController {

    private final CreatePostService createPostService;

    @PostMapping("/api/v1/posts")
    public ResponseEntity<BaseResponse<Void>> createPost(@RequestBody @Valid final CreatePostCommand createPostCommand) {
        createPostService.create(createPostCommand.authorId(), createPostCommand.title(), createPostCommand.content());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponse.created());
    }
}
