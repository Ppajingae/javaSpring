package com.yoong.javaspring2.advanced.trace.thread_local.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalService {

    private final ThreadLocal<String> nameStore = new ThreadLocal<>();

    public String logic(String name) {
        log.info("저장됨 name = {} -> nameStore = {}", name, nameStore.get());
        nameStore.set(name);
        ThreadUtils.sleep(1000);
        log.info("조회 nameStore = {}", nameStore.get());
        return nameStore.get();
    }



}
