package be.vdab.movies.repositories;

public interface ReservatieRepository {
    long voegReservatieToe(long klantId, long filmId);
}
