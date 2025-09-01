package atec.poo.cferro.app.exceptions;

import atec.poo.ui.exceptions.DialogException;

public class AppNoSuchEstacaoException extends DialogException {

    private String estacao;

    public AppNoSuchEstacaoException(String estacao) {
        this.estacao = estacao;
    }

    @Override
    public String getMessage() {
        return Labels.noSuchEstacao(this.estacao);
    }
}
