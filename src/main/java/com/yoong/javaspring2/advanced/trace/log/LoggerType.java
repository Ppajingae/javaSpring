package com.yoong.javaspring2.advanced.trace.log;

import lombok.Getter;

@Getter
public enum LoggerType {
    START_PREFIX("-->"),
    COMPLETE_PREFIX("<--"),
    EX_PREFIX("<X-");

    private final String value;

    LoggerType(String value) {
        this.value = value;
    }

}
