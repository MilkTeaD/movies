package be.vdab.movies.services;

import be.vdab.movies.domein.Film;
import be.vdab.movies.repositories.FilmsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
class DefaultFilmsService implements FilmsService{
    private final FilmsRepository filmsRepository;

    DefaultFilmsService(FilmsRepository filmsRepository) {
        this.filmsRepository = filmsRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Film> geefAlleFilmsVolgensGenreIdGesorteerdVolgensId(long genreId) {
        return filmsRepository.geefAlleFilmsVolgensGenreIdGesorteerdVolgensId(genreId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Film> geefFilmVolgensId(long filmId) {
        return filmsRepository.geefFilmVolgensId(filmId);
    }
}
