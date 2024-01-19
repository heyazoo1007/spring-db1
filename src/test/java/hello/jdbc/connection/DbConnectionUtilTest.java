package hello.jdbc.connection;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

@Slf4j
class DbConnectionUtilTest {

    @Test
    void getConnection() {
        Connection connection = DbConnectionUtil.getConnection();
        Assertions.assertThat(connection).isNotNull();
    }
}
