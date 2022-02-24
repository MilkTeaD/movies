package be.vdab.movies.repositories;

import be.vdab.movies.exceptions.FilmAlGereserveerdException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Map;

@Repository
class JDBCReservatieRepository implements ReservatieRepository{
    private final JdbcTemplate template;
    private final SimpleJdbcInsert insert;

    JDBCReservatieRepository(JdbcTemplate template) {
        this.template = template;
        insert = new SimpleJdbcInsert(template)
                .withTableName("reservaties");
    }

    @Override
    public long voegReservatieToe(long klantId, long filmId) {
        try {
            return insert.execute(
                    Map.of("klantId", klantId,
                            "filmId", filmId,
                            "reservatie", LocalDateTime.now())
            );
        } catch (Exception e) {
            throw new FilmAlGereserveerdException();
        }
    }
}
