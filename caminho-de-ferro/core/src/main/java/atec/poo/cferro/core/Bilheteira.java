package atec.poo.cferro.core;
import java.time.LocalDate;


public class Bilheteira {
    private LocalDate today;
    public static LocalDate TODAY;
    //TODO: Completar

    public Bilheteira() {
        this.today = LocalDate.of(2025, 7, 28);
        Bilheteira.TODAY = this.today;
        //TODO: Completar
    }

    public void incrementDate(int numDias) {
        if (numDias <= 0)
            return;
        this.today = this.today.plusDays(numDias);
        Bilheteira.TODAY = this.today;
    }


    //TODO: Completar


}