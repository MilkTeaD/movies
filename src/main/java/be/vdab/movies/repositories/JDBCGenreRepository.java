package be.vdab.movies.repositories;

import be.vdab.movies.domein.Genre;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
class JDBCGenreRepository implements GenreRepository{
    private final JdbcTemplate template;
    private final RowMapper<Genre> genreRowMapper =
            (result,rowNum) -> new Genre(
                    result.getLong("id"),
                    result.getString("naam")
            );

    JDBCGenreRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Genre> geefAlleGenresAlfabetisch() {
        var sql= """
                select id,naam
                from genres
                order by naam
                """;
        return template.query(sql,genreRowMapper);
    }

    @Override
    public Optional<Genre> geefGenreVolgensId(long genreId) {
        try {
            var sql = """
                select id,naam
                from genres
                where id=?
                """;
            return Optional.of( template.queryForObject(sql,genreRowMapper,genreId));
        } catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }
    }
}
