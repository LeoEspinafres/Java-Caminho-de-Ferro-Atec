package atec.poo.cferro.app.main;

import atec.poo.cferro.app.exceptions.AppInvalidNumberOfDaysException;
import atec.poo.cferro.core.CaminhoFerro;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.exceptions.DialogException;

import java.time.LocalDate;

public class DoIncrementDate extends Comando<CaminhoFerro> {
    private LerInteiro dias;

    public DoIncrementDate(CaminhoFerro caminhoFerro) {
        super(caminhoFerro, Labels.DoIncrementDays);
        this.dias=new LerInteiro(Labels.ASK_DIAS);
    }

    @Override
    public void executar() throws DialogException {
        ui.lerInput(this.dias);
        if(this.dias.getValor() < 1)
            throw new AppInvalidNumberOfDaysException(this.dias.getValor());
        this.getReceptor().incrementDate(this.dias.getValor());
        ui.escreveLinha(Labels.dataAtualAtualizada(this.dias.getValor()));

    }
}
