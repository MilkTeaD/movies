package be.vdab.movies.services;

import be.vdab.movies.domein.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    List<Genre> geefAlleGenresAlfabetisch();
    Optional<Genre> geefGenreVolgensId(long genreId);
}
