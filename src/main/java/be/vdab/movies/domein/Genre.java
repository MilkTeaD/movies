package be.vdab.movies.domein;

public class Genre {
    private final long id;
    private final String genreNaam;

    public Genre(long id, String genreNaam) {
        this.id = id;
        this.genreNaam = genreNaam;
    }

    public long getId() {
        return id;
    }

    public String getGenreNaam() {
        return genreNaam;
    }
}
