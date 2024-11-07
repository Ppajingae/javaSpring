package com.yoong.javaspring2.advanced.trace.log_trace;

import com.yoong.javaspring2.advanced.trace.TraceStatus;

public interface LogTrace {

    TraceStatus begin(String message);
    void end(TraceStatus status);
    void exception(TraceStatus status, Exception exception);
}
