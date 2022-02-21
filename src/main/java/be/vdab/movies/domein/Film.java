package be.vdab.movies.domein;

import java.math.BigDecimal;

public class Film {
    private final long filmId;
    private final long genreId;
    private final String filmTitel;
    private long voorraad;
    private long gereserveerd;
    private final BigDecimal prijs;

    public Film(long id, long genreId, String filmTitel, long voorraad, long gereserveerd, BigDecimal prijs) {
        this.filmId = id;
        this.genreId = genreId;
        this.filmTitel = filmTitel;
        this.voorraad = voorraad;
        this.gereserveerd = gereserveerd;
        this.prijs = prijs;
    }

    public long getFilmId() {
        return filmId;
    }

    public long getGenreId() {
        return genreId;
    }

    public String getFilmTitel() {
        return filmTitel;
    }

    public long getVoorraad() {
        return voorraad;
    }

    public long getGereserveerd() {
        return gereserveerd;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public void setVoorraad(long voorraad) {
        this.voorraad = voorraad;
    }

    public void setGereserveerd(long gereserveerd) {
        this.gereserveerd = gereserveerd;
    }

    public long getBeschikBaar(){
        return voorraad-gereserveerd;
    }
}
