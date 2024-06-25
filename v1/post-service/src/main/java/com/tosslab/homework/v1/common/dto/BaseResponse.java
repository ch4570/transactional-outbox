package com.tosslab.homework.v1.common.dto;

import lombok.RequiredArgsConstructor;


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
}
