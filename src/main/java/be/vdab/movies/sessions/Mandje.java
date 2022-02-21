package be.vdab.movies.sessions;

import be.vdab.movies.domein.Film;
import be.vdab.movies.exceptions.FilmAlGereserveerdException;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Mandje implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final List<Long> lijstVanTeReserverenFilms = new ArrayList<>();

    public void mandjeLeegMaken() {
        lijstVanTeReserverenFilms.clear();
    }

    public List<Long> getLijstFilmIds() {
        return lijstVanTeReserverenFilms;
    }

    public void voegFilmToe(long filmId) {
        if (lijstVanTeReserverenFilms.isEmpty()) {
            lijstVanTeReserverenFilms.add(filmId);
        } else {
            // film kan slechts één keer bijgevoegd worden
            if (lijstVanTeReserverenFilms.stream().noneMatch(x->x==filmId)) {
                // filmId zit er nog niet in
                lijstVanTeReserverenFilms.add(filmId);
            } else {
                // filmId zit er al in
                throw new FilmAlGereserveerdException();
            }
        }
    }

    public long sizeOfMandje() {
        return lijstVanTeReserverenFilms.size();
    }
}
