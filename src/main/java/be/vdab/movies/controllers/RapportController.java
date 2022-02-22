package be.vdab.movies.controllers;

import be.vdab.movies.domein.Film;
import be.vdab.movies.services.FilmsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.model.IModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Controller
@RequestMapping("rapport")
public class RapportController {
    private final FilmsService filmsService;

    public RapportController(FilmsService filmsService) {
        this.filmsService = filmsService;
    }

    @GetMapping
    public ModelAndView rapport(String lijstMislukteReservaties){
        System.out.println("ff wachte");
    if (lijstMislukteReservaties != null) {
        System.out.println("not null");
        var mislukteLijst = new ArrayList<Film>();
        Arrays.stream(lijstMislukteReservaties.split(","))
                .map(filmId-> filmsService.geefFilmVolgensId(Long.parseLong(filmId)))
                .forEach(optionalVanFilm->optionalVanFilm.ifPresent(film -> mislukteLijst.add(film)));
        return new ModelAndView("rapport").addObject("mislukteLijst",mislukteLijst);
    }
    else {
        // allgoed gelukt
        return new ModelAndView("rapport");

    }

    }
}
