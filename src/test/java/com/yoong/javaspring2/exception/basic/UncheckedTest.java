package com.yoong.javaspring2.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class UncheckedTest {

    static class MyUnCheckedException extends RuntimeException {
        public MyUnCheckedException(String message) {
            super(message);
        }
    }

    @Test
    void unCheckedTest(){

        Service service = new Service();

        service.callCatch();
    }

    @Test
    void unCheckedThrowTest(){

        Service service = new Service();

        Assertions.assertThatThrownBy(()->service.callThrow())
                .isInstanceOf(MyUnCheckedException.class);
    }

    static class Service{

        Repository repository = new Repository();

        public void callCatch(){

            try {
                repository.call();
            }catch (MyUnCheckedException e){
                log.info("예외 처리 메세지 = {}", e.getMessage(), e);
            }

        }

        public void callThrow(){
            repository.call();
        }
    }

    static class Repository{

        public void call() {
            throw new MyUnCheckedException("ex");
        }
    }
}
