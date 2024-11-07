package com.yoong.javaspring2.advanced;

import com.yoong.javaspring2.advanced.trace.log_trace.FieldLogTrace;
import com.yoong.javaspring2.advanced.trace.log_trace.LogTrace;
import com.yoong.javaspring2.advanced.trace.log_trace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }
}
