package atec.poo.cferro.app.main;

import atec.poo.cferro.core.CaminhoFerro;
import atec.poo.ui.Comando;

public class Menu extends atec.poo.ui.Menu {
    public Menu(CaminhoFerro cf) {
        super(Labels.MENU_PRINCIPAL, new Comando<?>[]{
                new DoOpen(cf),
                new DoSave(cf),
                new DoIncrementDate(cf),
                new DoOpenPassageiros(cf),
                new DoOpenServicos(cf),
                new DoOpenItinerarios(cf)
        });
    }
}
