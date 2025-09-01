package atec.poo.cferro.app.passageiros;

import atec.poo.cferro.core.CaminhoFerro;
import atec.poo.ui.Comando;

public class Menu extends atec.poo.ui.Menu {
    public Menu(CaminhoFerro cf) {
        super(Labels.MENU_PASSAGEIROS, new Comando<?>[]{
                new DoRegistUser(cf),
                new DoShowPassenger(cf),
                new DoShowPassengers(cf),
                new DoShowPassengersByAge(cf),
                new DoListPassengersByTravels(cf),
                new DoChangePassengerName(cf),
                new DoSearchPassengers(cf)
        });
    }
}
