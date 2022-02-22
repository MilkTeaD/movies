package be.vdab.movies.repositories;

import be.vdab.movies.domein.Film;
import be.vdab.movies.exceptions.FilmAlGereserveerdException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class JDBCFilmsRepository implements FilmsRepository{
    private final JdbcTemplate template;
    private final RowMapper<Film> filmRowMapper =
            (result,rowNum) -> new Film(
                    result.getLong("id"),
                    result.getLong("genreId"),
                    result.getString("titel"),
                    result.getLong("voorraad"),
                    result.getLong("gereserveerd"),
                    result.getBigDecimal("prijs")
            );


    JDBCFilmsRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Film> geefAlleFilmsVolgensGenreIdGesorteerdVolgensId(long genreId) {
        var sql= """
                select id,genreId,titel,voorraad,gereserveerd,prijs
                from films
                where genreId = ?
                order by id
                """;
        return template.query(sql,filmRowMapper,genreId);
    }

    @Override
    public Optional<Film> geefFilmVolgensId(long filmId) {
        try {
            var sql= """
                        select id,genreId,titel,voorraad,gereserveerd,prijs
                        from films
                        where id=?
                    """;
            return Optional.of(template.queryForObject(sql,filmRowMapper,filmId));
        } catch(IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Film> findAndLockById(long filmId) {
        try {
            var sql= """
                        select id,genreId,titel,voorraad,gereserveerd,prijs
                        from films
                        where id=?
                        for update
                    """;
            return Optional.of(template.queryForObject(sql,filmRowMapper,filmId));
        } catch(IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Boolean updateFilmsMetReservatie(long filmId) {
        // verondersteld wordt dat de record gelockt werd voor update
        // en dat de film niet gedeleted werd gedurende de update
        try {
            var sql = """
                    update films
                    set gereserveerd = gereserveerd+1
                    where id=?
                    """;
            if (template.update(sql,filmId)==0){
                throw new FilmAlGereserveerdException();
            }
        } catch (IncorrectResultSizeDataAccessException exception) {
            throw new FilmAlGereserveerdException();
        }

        return false;
    }
}
