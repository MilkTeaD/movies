package be.vdab.movies.services;

import be.vdab.movies.domein.Film;
import be.vdab.movies.exceptions.FilmAlGereserveerdException;
import be.vdab.movies.repositories.FilmsRepository;
import be.vdab.movies.repositories.ReservatieRepository;
import be.vdab.movies.sessions.Mandje;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.TreeSet;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class DefaultReservatieServiceTest {
    private DefaultReservatieService service;
    @Mock
    private ReservatieRepository reservatieRepository;
    @Mock
    private FilmsRepository filmsRepository;
    @Mock
    private Mandje mandje;
    @BeforeEach
    void beforeEach() {
        service= new DefaultReservatieService(reservatieRepository,filmsRepository,mandje);

    }

    @Test
    @DisplayName("voeg een reservatie toe als er geen meer vrij zijn")
    void name(){
        when(filmsRepository.findAndLockById(1L))
                .thenReturn(Optional.of(new Film(
                        1L, 2L,"FlutFilm",
                        7,7, //7 in voorraad 6 gereserveerd
                        BigDecimal.valueOf(3.32)
                )));
/*        when(filmsRepository.findAndLockById(2L))
                .thenReturn(Optional.of(new Film(
                        2L, 2L,"AndereFlutFilm",
                        7,6, //7 in voorraad 6 gereserveerd
                        BigDecimal.valueOf(3.32)
                )));
*/
/*        when(reservatieRepository.voegReservatieToe(1L,1L))
                .thenReturn(1L); //id van reservatie */
        when(mandje.getLijstFilmIds()).thenReturn(new TreeSet<Long>(Arrays.asList(1L)));
//        when(mandje.sizeOfMandje()).thenReturn(1L);

        assertThat(service.voerReservatieUit(1L,mandje)).hasSize(1); // film al gereserveerd
        //assertThatExceptionOfType(FilmAlGereserveerdException.class).isThrownBy(() -> service.voerReservatieUit(1L,mandje));
    }
}
