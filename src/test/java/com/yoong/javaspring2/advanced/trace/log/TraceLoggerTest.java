package com.yoong.javaspring2.advanced.trace.log;

import com.yoong.javaspring2.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class TraceLoggerTest {

    @Test
    void beginEnd(){
        TraceLogger traceLogger = new TraceLogger();
        TraceStatus traceStatus = traceLogger.begin("hello");
        traceLogger.end(traceStatus);
    }

    @Test
    void beginException(){
        TraceLogger traceLogger = new TraceLogger();
        TraceStatus traceStatus = traceLogger.begin("hello");
        traceLogger.exception(traceStatus, new IllegalStateException("ex"));
    }

}