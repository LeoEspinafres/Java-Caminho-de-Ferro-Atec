package atec.poo.cferro.app.main;

import atec.poo.cferro.app.passageiros.Menu;
import atec.poo.cferro.core.CaminhoFerro;
import atec.poo.ui.Comando;
import atec.poo.ui.exceptions.DialogException;

public class DoOpenPassageiros extends Comando<CaminhoFerro> {

    public DoOpenPassageiros(CaminhoFerro caminhoFerro) {
        super(caminhoFerro, Labels.DoOpenPassageiros);
    }

    @Override
    public void executar() throws DialogException {
        Menu menu=new Menu(this.getReceptor());
        menu.open();
    }
}
