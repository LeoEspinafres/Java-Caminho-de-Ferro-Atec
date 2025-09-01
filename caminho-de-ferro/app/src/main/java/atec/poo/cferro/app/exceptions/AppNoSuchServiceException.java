package atec.poo.cferro.app.exceptions;

import atec.poo.ui.exceptions.DialogException;

public class AppNoSuchServiceException extends DialogException {

    private int id;

    public int getId() {
        return id;
    }

    public AppNoSuchServiceException(int id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return Labels.noSuchService(this.id);
    }
}
