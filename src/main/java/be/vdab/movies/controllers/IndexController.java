package be.vdab.movies.controllers;

import be.vdab.movies.services.FilmsService;
import be.vdab.movies.services.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
class IndexController {
    private final GenreService genreService;
    private final FilmsService filmsService;

    public IndexController(GenreService genreService, FilmsService filmsService) {
        this.genreService = genreService;
        this.filmsService = filmsService;
    }

    @GetMapping("/")
    public ModelAndView index() {

        return new ModelAndView("index","genres",genreService.geefAlleGenresAlfabetisch())
                .addObject("startGeenFout",true);
    }
    @GetMapping("{genreId}")
    public ModelAndView toonFilms(@PathVariable long genreId) {
        var modelAndView= new ModelAndView("index","genres",genreService.geefAlleGenresAlfabetisch())
                .addObject("films",filmsService.geefAlleFilmsVolgensGenreIdGesorteerdVolgensId(genreId));
        genreService.geefGenreVolgensId(genreId).ifPresent(genre ->modelAndView.addObject(genre));
        return modelAndView;
    }
}
