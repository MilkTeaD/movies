package be.vdab.movies.repositories;

import be.vdab.movies.domein.Film;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface FilmsRepository {
    List<Film> geefAlleFilmsVolgensGenreIdGesorteerdVolgensId(long genreId);
    Optional<Film> geefFilmVolgensId(long filmId);
    Boolean updateFilmsMetReservatie(long filmId);
    Optional<Film> findAndLockById(long filmId);
}
