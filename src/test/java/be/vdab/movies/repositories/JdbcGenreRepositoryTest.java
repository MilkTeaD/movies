package be.vdab.movies.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import static org.assertj.core.api.Assertions.assertThat;
@JdbcTest
@Import(JDBCGenreRepository.class)
@Sql("/insertTestValues.sql")
public class JdbcGenreRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final JDBCGenreRepository repository;
    private static final String GENRES = "genres";

    public JdbcGenreRepositoryTest(JDBCGenreRepository repository) {
        this.repository = repository;
    }

    @Test
    @DisplayName("Lijst van Alle Genres alfabetisch")
    void name() {
        assertThat(repository.geefAlleGenresAlfabetisch())
                .hasSize(countRowsInTable(GENRES))
                .extracting(element ->element.getGenreNaam())
                .isSorted();
    }

    private long idDollyWoodCrap() {
        return jdbcTemplate.queryForObject("select id from genres where naam ='DollyWoodCrap'",long.class);
    }

    @Test
    @DisplayName("test Id DollyWoodCrap")
    void name2() {
        assertThat(repository.geefGenreVolgensId(idDollyWoodCrap()))
                .hasValueSatisfying(genre -> assertThat(genre.getGenreNaam()).isEqualTo("DollyWoodCrap"));
    }

    @Test
    @DisplayName("onbestaande genre geeft empty")
    void name3() {
        assertThat(repository.geefGenreVolgensId(-1l)).isEmpty();
    }
}
