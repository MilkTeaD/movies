package be.vdab.movies.repositories;

import be.vdab.movies.domein.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    List<Genre> geefAlleGenresAlfabetisch();
    Optional<Genre> geefGenreVolgensId(long genreId);
}
