package be.vdab.movies.domein;

import java.time.LocalDate;

public class Reservatie {
    private final long klantId;
    private final long filmId;
    private final LocalDate reservatieDatum;

    public Reservatie(long klantId, long filmId, LocalDate reservatieDatum) {
        this.klantId = klantId;
        this.filmId = filmId;
        this.reservatieDatum = reservatieDatum;
    }

    public long getKlantId() {
        return klantId;
    }

    public long getFilmId() {
        return filmId;
    }

    public LocalDate getReservatieDatum() {
        return reservatieDatum;
    }
}
