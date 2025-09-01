package atec.poo.cferro.app.exceptions;

import atec.poo.ui.exceptions.DialogException;

public class AppNoPassengersFoundException extends DialogException {

    private final String searchTerm;

    public AppNoPassengersFoundException(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public String getMessage() {
        return Labels.noPassengersFound(searchTerm);
    }
}
