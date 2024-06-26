package com.tosslab.homework.v1.common.exception;

import com.tosslab.homework.v1.common.dto.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionCatcher {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<BaseResponse<Object>> catchBindException(BindException e) {

        log.error("BindException 발생 = [{}], 발생 시각 = [{}]", e, LocalDateTime.now());

        final Map<String, List<String>> errorMap = e.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())
                ));

        return ResponseEntity.badRequest()
                .body(BaseResponse.badRequest(errorMap));
    }
}
