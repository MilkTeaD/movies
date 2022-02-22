package be.vdab.movies.services;

import be.vdab.movies.domein.Klant;

import java.util.List;
import java.util.Optional;

public interface KlantService {
    List<Klant> geefGesorteerdeLijstGevondenKlanten(String zoekString);
    Optional<Klant> geefKlantVolgensId(long klantId);
}
