package be.vdab.movies.forms;

import javax.validation.constraints.NotBlank;

public record ZoekFormulier(
        @NotBlank
        String deelnaam,
        long klantId,
        long filmId
) {
}
