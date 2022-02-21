package be.vdab.movies.repositories;

import be.vdab.movies.domein.Film;

import java.util.List;

public interface FilmsRepository {
    List<Film> geefAlleFilmsVolgensGenreIdGesorteerdVolgensId(long genreId);
    Film geefFilmVolgensId(long filmId);
    Boolean updateFilmsMetReservatie(long filmId);
}
