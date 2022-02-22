package be.vdab.movies.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(JDBCKlantRepository.class)
@Sql("/insertTestValues.sql")
public class JdbcKlantRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final JDBCKlantRepository repository;
    private static final String KLANTEN="klanten";

    public JdbcKlantRepositoryTest(JDBCKlantRepository repository) {
        this.repository = repository;
    }

    private long getTestKlantId() {
        return jdbcTemplate.queryForObject("select id from klanten where familienaam='Test' and voornaam='Test'  ",long.class);
    }


    @Test
    @DisplayName("zoek like %est% = 2")
    void name3() {
        assertThat(repository.geefGesorteerdeLijstGevondenKlanten("Test"))
                .hasSize(2)
                .extracting(klant -> klant.getFamilieNaam().toUpperCase(Locale.ROOT))
                .isSorted();
    }

    @Test
    @DisplayName("zoek lijst van klanten")
    void name() {
        assertThat(repository.geefGesorteerdeLijstGevondenKlanten(""))
                .hasSize(countRowsInTable("klanten"))
                .extracting(klant ->klant.getFamilieNaam().toUpperCase(Locale.ROOT))
                .isSorted();
    }

    @Test
    @DisplayName("ongeldige id geeft empty")
    void name5() {
        assertThat(repository.geefKlantVolgensId(-1)).isEmpty();
    }

    @Test
    @DisplayName("zoek klant met id van Test")
    void name6() {
        assertThat(repository.geefKlantVolgensId(getTestKlantId()))
                .hasValueSatisfying(klant->assertThat(klant.getAdres().toUpperCase(Locale.ROOT).equals("TEST")));
    }
}
