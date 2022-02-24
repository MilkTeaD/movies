package be.vdab.movies.repositories;

import be.vdab.movies.exceptions.FilmAlGereserveerdException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@JdbcTest
@Import(JDBCReservatieRepository.class)
@Sql("/insertTestValues.sql")
public class JdbcReservatieRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final JDBCReservatieRepository repository;
    private static final String RESERVATIES = "reservaties";

    public JdbcReservatieRepositoryTest(JDBCReservatieRepository repository) {
        this.repository = repository;
    }

    private long getTestKlantId() {return jdbcTemplate.queryForObject("select id from klanten where familienaam='Test' and voornaam='Test'  ", long.class);}
    private long idAndereFlutFilm() {return jdbcTemplate.queryForObject("select id from films where titel='FlutFilm'", long.class);}private long idHansGrietje() {return jdbcTemplate.queryForObject("select id from films where titel='SuperHans en SpiderGrietje'", long.class);}

    @Test
    @DisplayName("Testen als een reservatie wel toegevoegd werd")
    void name() {
        System.out.println("KlantId: "+getTestKlantId());
        System.out.println("idFlutFilm"+idAndereFlutFilm());
        assertThat(repository.voegReservatieToe(getTestKlantId(),idAndereFlutFilm())).isPositive();

    }

    @Test
    @DisplayName("Een verkeerd klantID ingeven geeft fout")
    void name2() {
        assertThatExceptionOfType(FilmAlGereserveerdException.class)
                .isThrownBy(
                        ()-> repository.voegReservatieToe(-1L,-1L)
                );
    }

}
