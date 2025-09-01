package atec.poo.cferro.app.exceptions;

import atec.poo.ui.exceptions.DialogException;

import java.time.LocalDate;

public class AppBadDateException extends DialogException {
    private LocalDate hoje;
    private LocalDate partida;

    public AppBadDateException(LocalDate hoje, LocalDate partida) {
        this.hoje = hoje;
        this.partida = partida;
    }

    @Override
    public String getMessage() {
        return String.format("Data de partida [%s] não é válida. Terá que ser posterior ou igual a %s.",this.partida.toString(),this.hoje.plusDays(1).toString());
    }
}
