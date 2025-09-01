package atec.poo.cferro.app.exceptions;

import atec.poo.ui.exceptions.DialogException;

public class AppNoPassengersRegisteredException extends DialogException {
    @Override
    public String getMessage() {
        return Labels.NoPassegenrsRegistered;
    }
}
