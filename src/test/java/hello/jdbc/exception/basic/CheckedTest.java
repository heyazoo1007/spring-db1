package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// checked exception 테스트 - catch or throws
@Slf4j
public class CheckedTest {

    @Test
    void checked_catch() {
        Service service = new Service();
        service.callCatch();
    }

    @Test
    void checked_throw() {
        Service service = new Service();
        Assertions.assertThatThrownBy(service::callThrow)
                .isInstanceOf(MyCheckedException.class);
    }


    /**
     * Exception 을 상속받으면 checked exception 이 됨.
     */
    // 컴파일러가 체크하는 checked exception
    static class MyCheckedException extends Exception {
        public MyCheckedException(String message) {
            super(message);
        }
    }

    /**
     * checked exception 은
     * 예외를 잡아서 처리하거나 던지거나 둘 중 하나를 *필수*로 선택해야 한다.
     */
    static class Service {
        Repository repository = new Repository();

        /**
         * 예외를 잡아서 처리하는 코드
         */
        public void callCatch() {
            try {
                repository.call();
            } catch (MyCheckedException e) {
                // 예외처리 로직
                log.info("예외 처리, message = {}", e.getMessage(), e);
            }
        }

        /**
         * checked exception 을 밖으로 던지는 코드
         * 체크 예외는 예외를 잡지 않고 밖으로 던지려면 throws 를 메서드에 필수로 선언해야 한다
         * @throws MyCheckedException
         */
        public void callThrow() throws MyCheckedException {
            repository.call();
        }
    }
    static class Repository {
        // checked exception 은 꼭 위로 throws 를 해야함
        public void call() throws MyCheckedException {
            throw new MyCheckedException("ex");
        }
    }
}
