package com.yoong.javaspring2.advanced.trace.log;

import com.yoong.javaspring2.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class TraceLoggerV2Test {

    @Test
    void beginEnd(){
        TraceLogger traceLogger = new TraceLogger();
        TraceStatus traceStatus1 = traceLogger.begin("hello");
        TraceStatus traceStatus2 = traceLogger.beginSync(traceStatus1.getTraceId(), "hello1");
        traceLogger.end(traceStatus2);
        traceLogger.end(traceStatus1);
    }

    @Test
    void beginException(){
        TraceLogger traceLogger = new TraceLogger();
        TraceStatus traceStatus1 = traceLogger.begin("hello");
        TraceStatus traceStatus2 = traceLogger.beginSync(traceStatus1.getTraceId(), "hello");
        traceLogger.exception(traceStatus2, new IllegalStateException("ex"));
        traceLogger.exception(traceStatus1, new IllegalStateException("ex"));
    }

}