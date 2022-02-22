package be.vdab.movies.services;

import be.vdab.movies.domein.Klant;
import be.vdab.movies.repositories.KlantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
class DefaultKlantService implements KlantService{
    private final KlantRepository klantRepository;

    DefaultKlantService(KlantRepository klantRepository) {
        this.klantRepository = klantRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Klant> geefGesorteerdeLijstGevondenKlanten(String zoekString) {
        return klantRepository.geefGesorteerdeLijstGevondenKlanten(zoekString);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Klant> geefKlantVolgensId(long klantId) {
        return klantRepository.geefKlantVolgensId(klantId);
    }
}
