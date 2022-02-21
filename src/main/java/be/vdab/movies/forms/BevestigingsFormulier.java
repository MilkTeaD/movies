package be.vdab.movies.forms;

import be.vdab.movies.domein.Film;

import java.util.List;

public record BevestigingsFormulier (
        List<Film> filmLijst,
        long klantId
) {
}
