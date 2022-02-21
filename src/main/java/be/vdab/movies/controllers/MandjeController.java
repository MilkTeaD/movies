package be.vdab.movies.controllers;

import be.vdab.movies.domein.Film;
import be.vdab.movies.services.FilmsService;
import be.vdab.movies.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/mandje")
public class MandjeController {
    private final Mandje mandje;
    private final FilmsService filmsService;

    public MandjeController(Mandje mandje, FilmsService filmsService) {
        this.mandje = mandje;
        this.filmsService = filmsService;
    }

    @GetMapping
    public ModelAndView toonMandje() {
        if (mandje.sizeOfMandje()==0) {
            return new ModelAndView("redirect:/");
        }
        var filmListMandje=new ArrayList<Film>();
        mandje.getLijstFilmIds().forEach(x->filmsService.geefFilmVolgensId(x).ifPresent(film->filmListMandje.add(film)));
        return new ModelAndView("mandje","filmListMandje",filmListMandje);
    }

    @PostMapping("verwijderen")
    public ModelAndView verwijderLijst(long[] filmIds) {
        if (filmIds != null) {
            Arrays.stream(filmIds).sequential().forEach(element -> mandje.deleteFilmId(element));
        }
        // verwerk lijst hier
        return new ModelAndView("redirect:/mandje");
    }
}
