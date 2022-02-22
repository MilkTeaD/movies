package be.vdab.movies.repositories;

import be.vdab.movies.domein.Klant;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class JDBCKlantRepository implements KlantRepository{
    private final JdbcTemplate template;
    private final RowMapper<Klant> klantRowMapper =
            (result, rowNum) -> new Klant(
                    result.getLong("id"),
                    result.getString("familienaam"),
                    result.getString("voornaam"),
                    result.getString("straatNummer"),
                    result.getLong("postcode"),
                    result.getString("gemeente")
            );

    JDBCKlantRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Klant> geefGesorteerdeLijstGevondenKlanten(String zoekString) {
        var sql = """
                select id, familienaam, voornaam, straatNummer, postcode, gemeente
                from klanten
                where familienaam like ?
                order by familienaam,voornaam
                """;
        return template.query(sql,klantRowMapper,(zoekString=="" || zoekString =="*") ? "%" : '%'+zoekString+'%');
    }

    @Override
    public Optional<Klant> geefKlantVolgensId(long klantId) {
        try {
            var sql = """
                    select id, familienaam, voornaam, straatNummer, postcode, gemeente
                    from klanten
                    where id = ?
                    """;
            return Optional.of(template.queryForObject(sql, klantRowMapper, klantId));
        } catch (IncorrectResultSizeDataAccessException exception) {
            return Optional.empty();
        }
    }

}
