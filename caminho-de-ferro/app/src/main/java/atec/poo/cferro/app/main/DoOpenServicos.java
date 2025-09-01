package atec.poo.cferro.app.main;

import atec.poo.cferro.app.servicos.Menu;
import atec.poo.cferro.core.CaminhoFerro;
import atec.poo.ui.Comando;
import atec.poo.ui.exceptions.DialogException;

public class DoOpenServicos extends Comando<CaminhoFerro> {

    public DoOpenServicos(CaminhoFerro caminhoFerro) {
        super(caminhoFerro, Labels.DoOpenservicos);
    }

    @Override
    public void executar() throws DialogException {
        Menu menu=new Menu(this.getReceptor());
        menu.open();
    }
}
