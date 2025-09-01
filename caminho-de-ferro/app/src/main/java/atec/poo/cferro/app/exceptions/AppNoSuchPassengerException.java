package atec.poo.cferro.app.exceptions;

import atec.poo.ui.exceptions.DialogException;

/**
 * Excepção para quando um utilizador não existe
 */
public class AppNoSuchPassengerException extends DialogException {


    private int id_passanger;

    /**
     * Construtor
     * @param id_passenger
     */
    public AppNoSuchPassengerException(int id_passenger){
        this.id_passanger=id_passenger;
    }

    /**
     * Mensagem de erro para cliente não encontrado
     * @return String
     */
    @Override
    public String getMessage() {
        return Labels.noSuchPassegenr(this.id_passanger);
    }
}
