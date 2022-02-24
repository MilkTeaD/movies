package be.vdab.movies.sessions;

import be.vdab.movies.domein.Film;
import be.vdab.movies.exceptions.FilmAlGereserveerdException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
@Component
@SessionScope
public class Mandje implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;
    private final TreeSet<Long> lijstVanTeReserverenFilms = new TreeSet<>();

    public void mandjeLeegMaken() {
        lijstVanTeReserverenFilms.clear();
    }

    public Set<Long> getLijstFilmIds() {
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

    public void deleteFilmId(long filmId) {
        lijstVanTeReserverenFilms.removeIf(element->element==filmId);
    }

    public long sizeOfMandje() {
        return lijstVanTeReserverenFilms.size();
    }
}
