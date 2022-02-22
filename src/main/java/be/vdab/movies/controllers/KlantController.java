package be.vdab.movies.controllers;

import be.vdab.movies.forms.ZoekFormulier;
import be.vdab.movies.services.KlantService;
import be.vdab.movies.services.ReservatieService;
import be.vdab.movies.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("klant")
class KlantController {
    private final KlantService klantService;
    private final Mandje mandje;
    private final ReservatieService reservatieService;

    KlantController(KlantService klantService, Mandje mandje, ReservatieService reservatieService) {
        this.klantService = klantService;
        this.mandje = mandje;
        this.reservatieService = reservatieService;
    }

    @GetMapping
    public ModelAndView toonZoekFormulier() {
        return new ModelAndView("klant").addObject(new ZoekFormulier(""));
    }

    @GetMapping("klantzoeken")
    public ModelAndView zoekLijstKlanten (@Valid ZoekFormulier zoekFormulier, Errors errors) {
        if (!errors.hasErrors()) {
            // no errors
            var modelAndView = new ModelAndView("klant")
                    .addObject("klantLijst",klantService.geefGesorteerdeLijstGevondenKlanten(zoekFormulier.deelNaam()));
            return modelAndView ;
        }
        //System.out.println("has errors");
        return new ModelAndView("klant");
    }

    @GetMapping("bevestigen/{klantId}")
    public ModelAndView bevestiging(@PathVariable long klantId) {
        var modelAndView = new ModelAndView("bevestigen")
                .addObject("aantal",mandje.sizeOfMandje())
                .addObject("klantId",klantId);
        klantService.geefKlantVolgensId(klantId)
                .ifPresent(klant -> modelAndView.addObject("klantVoorNaamFamilieNaam",klant.getVoorNaam() + ' '+klant.getFamilieNaam()));

        return modelAndView;
    }

    @PostMapping("reserveren/{klantId}")
    public ModelAndView reserveren(@PathVariable long klantId, RedirectAttributes redirect) {
        var modelAndView = new ModelAndView("redirect:/rapport");
        // reservatie doen (service) : geeft lijst van mislukte reservaties
        var mislukteLijst = reservatieService.voerReservatieUit(klantId,mandje);
        // redirect attributes : lijst van mislukte
        if (! mislukteLijst.isEmpty()) {
            redirect.addAttribute("lijstMislukteReservaties", mislukteLijst.stream().map(String::valueOf).collect(Collectors.joining(",")));
            //modelAndView.addObject("lijstMislukteReservaties",mislukteLijst);
        }
        return modelAndView;
    }

}
