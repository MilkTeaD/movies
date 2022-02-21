package be.vdab.movies.services;

import be.vdab.movies.domein.Film;

import java.util.List;
import java.util.Optional;

public interface FilmsService {
    List<Film> geefAlleFilmsVolgensGenreIdGesorteerdVolgensId(long genreId);
    Optional<Film> geefFilmVolgensId(long filmId);
}
