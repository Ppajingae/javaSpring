package com.yoong.javaspring2.exception.basic;


import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class CheckedTest {

    // Exception 을 상속 받은 예외는 예외 체크가 가능 하다
    static class MyCheckedException extends Exception {

        public MyCheckedException(String message) {
            super(message);
        }
    }

    @Test
    void checkedTest() {

        Service service = new Service();

        service.callCatch();
    }

    @Test
    void checkedThrow() {

        Service service = new Service();

        Assertions.assertThatThrownBy(()->service.callThrow())
                .isInstanceOf(MyCheckedException.class);
    }

    // checked 예외는 필수 적으로 throw 하거나 잡아야 한다
    static class Service{
        Repository repository = new Repository();

        // 예외를 잡아서 처리 하는 코드
        public void callCatch(){

            try {
                repository.call();
            } catch (MyCheckedException e) {
                log.info("예외 처리 메세지 = {}", e.getMessage(), e);
            }
        }

        public void callThrow() throws MyCheckedException {
            repository.call();
        }

    }

    static class Repository{
        public void call() throws MyCheckedException {
            throw new MyCheckedException("ex");
        }
    }
}
