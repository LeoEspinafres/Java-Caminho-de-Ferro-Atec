package atec.poo.cferro.app.main;

import atec.poo.cferro.app.itinerarios.Menu;
import atec.poo.cferro.core.CaminhoFerro;
import atec.poo.ui.Comando;
import atec.poo.ui.exceptions.DialogException;

public class DoOpenItinerarios extends Comando<CaminhoFerro> {
    public DoOpenItinerarios(CaminhoFerro caminhoFerro) {
        super(caminhoFerro, Labels.DoOpenItinerarios);
    }

    @Override
    public void executar() throws DialogException {
        Menu menu=new Menu(this.getReceptor());
        menu.open();
    }
}
