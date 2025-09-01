package atec.poo.cferro.app.servicos;

import atec.poo.cferro.core.CaminhoFerro;
import atec.poo.ui.Comando;

public class Menu extends atec.poo.ui.Menu {

    public Menu(CaminhoFerro cf) {
        super(Labels.MENU_SERVICOS , new Comando<?>[]{
                new DoShowTodasEstacoes(cf,Labels.ShowEstacoes),
                new DoShowTodosServicos(cf,Labels.ShowTodosServicos),
                new DoShowServicoNumero(cf,Labels.ShowServicoNumero),
                new DoShowServicosOrigemEm(cf,Labels.ShowServicosInicioEm),
                new DoShowServicosFimEm(cf,Labels.ShowServicosFimEm),
                new DoShowServicosServemEstacao(cf,Labels.ShowServicosServemEstacao),
                new DoShowSegmentosServico(cf,Labels.ShowSegmentosServico)
        });
    }
}
