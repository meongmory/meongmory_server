package com.meongmory.meongmory.domain.notification.entity;

import lombok.Getter;

@Getter
public enum NotificationType {
    NEWS("소식"),
    COMMENT("댓글");

    private final String name;

    NotificationType(String name) {
        this.name = name;
    }
}
