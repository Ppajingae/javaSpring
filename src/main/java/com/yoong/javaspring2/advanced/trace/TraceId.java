package com.yoong.javaspring2.advanced.trace;

import lombok.Getter;

import java.util.UUID;

@Getter
public class TraceId {

    private final String traceId;
    private final int level;

    public TraceId(){
        this.traceId = createTraceId();
        this.level = 0;
    }

    public TraceId(String traceId, int level) {
        this.traceId = traceId;
        this.level = level;
    }

    private String createTraceId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public TraceId createNextTraceId() {
        return new TraceId(traceId, level + 1);
    }

    public TraceId createPreviousTraceId() {
        return new TraceId(traceId, level - 1);
    }

    public boolean isFirstLevel(){
        return level == 0;
    }


}
