package lucauzeri.S2D2.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super("Il post con id: " + id + " non è stato trovato");
    }
}
