package be.vdab.movies.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(JDBCFilmsRepository.class)
@Sql("/insertTestValues.sql")
public class JdbcFilmsRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String FILMS = "films";
    private final JDBCFilmsRepository repository;

    public JdbcFilmsRepositoryTest(JDBCFilmsRepository jdbcFilmsRepository) {
        this.repository = jdbcFilmsRepository;
    }
    private long idFlutFilm() {
        return jdbcTemplate.queryForObject("select id from genres where naam='AndereFlutFilms'",long.class);
    }

    private long idDollyWoodCrap() {
        return jdbcTemplate.queryForObject("select id from genres where naam ='DollyWoodCrap'",long.class);
    }

    private long idAndereFlutFilm() {
        return jdbcTemplate.queryForObject("select id from films where titel='AndereFlutFilm'",long.class);
    }

    private long idHansGrietje() {
        return jdbcTemplate.queryForObject("select id from films where titel='SuperHans en SpiderGrietje'",long.class);
    }
    @Test
    @DisplayName("test genreId van AndereFlutFilm")
    void name() {
        assertThat(repository.geefFilmVolgensId(idAndereFlutFilm())).hasValueSatisfying(film->assertThat(film.getGenreId()).isEqualTo(idFlutFilm()));
    }
}
