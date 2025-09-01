package atec.poo.cferro.app.exceptions;

import atec.poo.ui.exceptions.DialogException;

public class AppInvalidNumberOfDaysException extends DialogException {
    private int numberOfDays;
    public AppInvalidNumberOfDaysException(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }
    @Override
    public String getMessage() {
        return Labels.invalidNumberOfDays(this.numberOfDays);
    }
}
