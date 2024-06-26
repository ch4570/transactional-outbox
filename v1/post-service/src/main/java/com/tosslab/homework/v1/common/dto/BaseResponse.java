package com.tosslab.homework.v1.common.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BaseResponse<T> {

    private final T data;
    private final int code;

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(data, 200);
    }

    public static <T> BaseResponse<T> success() {
        return new BaseResponse<>(null, 200);
    }

    public static <T> BaseResponse<T> badRequest(T data) {
        return new BaseResponse<>(data, 400);
    }

    public static <T> BaseResponse<T> created() {
        return new BaseResponse<>(null, 201);
    }
}
