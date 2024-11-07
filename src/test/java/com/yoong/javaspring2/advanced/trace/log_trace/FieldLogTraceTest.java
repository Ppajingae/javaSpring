package com.yoong.javaspring2.advanced.trace.log_trace;

import com.yoong.javaspring2.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class FieldLogTraceTest {

    FieldLogTrace trace = new FieldLogTrace();

    @Test
    void  beginAndLevel2(){

        TraceStatus status1 = trace.begin("test1");
        TraceStatus status2 = trace.begin("test2");
        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void  beginExceptionLevel2(){

        TraceStatus status1 = trace.begin("test1");
        TraceStatus status2 = trace.begin("test2");
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }
}