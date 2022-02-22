package be.vdab.movies.services;

import be.vdab.movies.sessions.Mandje;

import java.util.List;

public interface ReservatieService {
    List<Long> voerReservatieUit(long klantId, Mandje mandje);
}
