package be.vdab.movies.controllers;

import be.vdab.movies.domein.Film;
import be.vdab.movies.exceptions.FilmAlGereserveerdException;
import be.vdab.movies.services.FilmsService;
import be.vdab.movies.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("/film")
class FilmController {
    private final FilmsService filmsService;
    private final Mandje mandje;

    FilmController(FilmsService filmsService, Mandje mandje) {
        this.filmsService = filmsService;
        this.mandje = mandje;
    }

    @GetMapping("{filmId}")
    public ModelAndView showFilm(@PathVariable long filmId) {
        var modelAndView = new ModelAndView("film");
        filmsService.geefFilmVolgensId(filmId).ifPresent(film->modelAndView.addObject(film));
        return modelAndView;
    }

    @PostMapping("mandje/{filmId}")
    public ModelAndView addToMandje(@PathVariable long filmId) {
            try {
                mandje.voegFilmToe(filmId);
            } catch (FilmAlGereserveerdException e) {
                var modelAndView = new ModelAndView("film");
                filmsService.geefFilmVolgensId(filmId).ifPresent(film->modelAndView.addObject(film));
                modelAndView.addObject("error","Film zit al in Mandje");
                return modelAndView;
            }
            var filmListMandje=new ArrayList<Film>();
            mandje.getLijstFilmIds().forEach(x->filmsService.geefFilmVolgensId(x).ifPresent(film->filmListMandje.add(film)));
            return new ModelAndView("mandje")
                     .addObject("filmListMandje",filmListMandje);
        }

}
