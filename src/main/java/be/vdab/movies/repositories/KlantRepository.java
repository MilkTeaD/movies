package be.vdab.movies.repositories;

import be.vdab.movies.domein.Klant;

import java.util.List;
import java.util.Optional;

public interface KlantRepository {
    List<Klant> geefGesorteerdeLijstGevondenKlanten(String zoekString);
    Optional<Klant> geefKlantVolgensId(long klantId);
}
