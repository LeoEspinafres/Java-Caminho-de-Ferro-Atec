package atec.poo.cferro.app.itinerarios;

import atec.poo.cferro.core.CaminhoFerro;
import atec.poo.ui.Comando;

public class Menu extends atec.poo.ui.Menu {
    public Menu(CaminhoFerro cf) {
        super(Labels.MENU_ITINERARIOS, new Comando[]{
                new DoRegistarItinerario(cf),
                new DoShowItinerarios(cf),
                new DoShowItinerariosClient(cf)
        });
    }
}
