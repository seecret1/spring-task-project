package com.github.seecret.spring_task.handler;

import java.time.LocalDateTime;

public record ErrorResponseDto(
        String message,

        String detailMessage,

        LocalDateTime errorTime
) {
}
