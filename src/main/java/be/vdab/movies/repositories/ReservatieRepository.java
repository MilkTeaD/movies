package be.vdab.movies.repositories;

public interface ReservatieRepository {
    boolean voegReservatieToe(long klantId, long filmId);
}
