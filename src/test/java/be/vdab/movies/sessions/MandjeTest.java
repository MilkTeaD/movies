package be.vdab.movies.sessions;

import be.vdab.movies.exceptions.FilmAlGereserveerdException;
import be.vdab.movies.repositories.FilmsRepository;
import be.vdab.movies.repositories.ReservatieRepository;

import be.vdab.movies.services.DefaultReservatieServiceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;


public class MandjeTest {
    private Mandje mandje;
    @BeforeEach
    void beforeEach() {
        mandje = new Mandje();
    }

    @Test
    @DisplayName("is een nieuw mandje leeg ? ")
    void name() {
        assertThat(mandje.sizeOfMandje()).isEqualTo(0);
        assertThat(mandje.getLijstFilmIds()).hasSize(0);
    }
    @Test
    @DisplayName("voeg één element toe en test dat juist dit element is teogevoegd")
    void name2(){
        mandje.voegFilmToe(1L);
        assertThat(mandje.getLijstFilmIds()).containsOnly(1L);
    }
    @Test
    @DisplayName("je kan geen twee keer dezelfe film toevoegen")
    void name3() {
        mandje.voegFilmToe(1L);

        assertThatExceptionOfType(FilmAlGereserveerdException.class).isThrownBy(() -> mandje.voegFilmToe(1L));
    }
}
