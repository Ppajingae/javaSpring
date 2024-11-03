package com.yoong.javaspring2.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Setter;

@Setter
public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void connect() {
        System.out.println("connect url = " + url);
    }

    public void call(String msg){
        System.out.println("call url = " + url +"call msg = " + msg);
    }

    public void disconnect() {
        System.out.println("disconnect url = " + url);
    }


    @PostConstruct
    public void init() throws Exception {
        connect();
        call("초기화 연결 메세지");
    }

    @PreDestroy
    public void close() throws Exception {
        disconnect();
    }
}
