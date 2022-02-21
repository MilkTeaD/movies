package be.vdab.movies.domein;

public class Genre {
    private final long genreId;
    private final String genreNaam;

    public Genre(long id, String genreNaam) {
        this.genreId = id;
        this.genreNaam = genreNaam;
    }

    public long getGenreId() {
        return genreId;
    }

    public String getGenreNaam() {
        return genreNaam;
    }
}
