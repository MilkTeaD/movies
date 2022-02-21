package be.vdab.movies.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import static org.assertj.core.api.Assertions.assertThat;
import javax.sql.DataSource;
import java.sql.SQLException;

@JdbcTest
class DataSourceTest {
    private final DataSource dataSource;

    public DataSourceTest(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Test
    @DisplayName("DataSource Test")
    void getConnection() throws SQLException {
        try (var connection = dataSource.getConnection()) {
            assertThat(connection.getCatalog()).isEqualTo("movies");
        }
    }
}
