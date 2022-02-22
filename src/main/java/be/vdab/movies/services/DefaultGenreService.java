package be.vdab.movies.services;

import be.vdab.movies.domein.Genre;
import be.vdab.movies.repositories.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
class DefaultGenreService implements GenreService{
    private final GenreRepository genreRepository;

    DefaultGenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> geefAlleGenresAlfabetisch() {

        return genreRepository.geefAlleGenresAlfabetisch();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Genre> geefGenreVolgensId(long genreId) {

        return genreRepository.geefGenreVolgensId(genreId);
    }
}
