package be.vdab.movies.repositories;

import be.vdab.movies.domein.Genre;

import java.util.List;

public interface GenreRepository {
    List<Genre> geefAlleGenresAlfabetisch();
    Genre geefGenreVolgensId(long genreId);
}
