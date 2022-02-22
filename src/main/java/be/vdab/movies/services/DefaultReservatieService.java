package be.vdab.movies.services;

import be.vdab.movies.exceptions.FilmAlGereserveerdException;
import be.vdab.movies.repositories.FilmsRepository;
import be.vdab.movies.repositories.ReservatieRepository;
import be.vdab.movies.sessions.Mandje;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
class DefaultReservatieService implements ReservatieService{
    private final ReservatieRepository reservatieRepository;
    private final FilmsRepository filmsRepository;
    private final Mandje mandje;

    DefaultReservatieService(ReservatieRepository reservatieRepository, FilmsRepository filmsRepository, Mandje mandje) {
        this.reservatieRepository = reservatieRepository;
        this.filmsRepository = filmsRepository;
        this.mandje=mandje;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<Long> voerReservatieUit(long klantId, Mandje mandje) {
        // return een lijst van niet gelukte (gevonden doch niet genoeg beschikbare) films
        var mislukteLijst = new ArrayList<Long>();
        // voor elk element van mandje
        mandje.getLijstFilmIds().stream()
                .forEach(filmId-> {
                    filmsRepository.findAndLockById(filmId)
                            .ifPresent(film -> {
                                        if (film.getBeschikBaar() > 0) {
                                            try {
                                                // als gelukt update films
                                                filmsRepository.updateFilmsMetReservatie(filmId);
                                                //verondererstellen gelukt want gelockt voor update
                                                reservatieRepository.voegReservatieToe(klantId, filmId);
                                            } catch (FilmAlGereserveerdException exception) {
                                                mislukteLijst.add(filmId);
                                            }
                                        } else {
                                            // film niet meer beschikbaar
                                            mislukteLijst.add(filmId);
                                        }
                                    }
                            ); // ifpresent
                });
        // hier bevat het mandje nog de filmIds van mislukte / niet (meer) bestaande films ... mandje wordt leeggemaakt ...
        mandje.mandjeLeegMaken();
        // opgelet : exceptions worden doorgegeven naar Controller-layer
        return mislukteLijst;
    }

}
