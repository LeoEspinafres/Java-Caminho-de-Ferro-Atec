package atec.poo.cferro.core;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class CaminhoFerro {
    private Bilheteira _b;

    public CaminhoFerro() {
        this._b = new Bilheteira();
    }

    /**
     * Incrementa a data num número inteiro de dias
     * @param dias Número inteiro de dias a incrementar
     */
    public void incrementDate(int dias){
        this._b.incrementDate(dias);
    }

    public void importFile(String dataFile) throws IOException {
        Scanner ler = new Scanner(new File(dataFile));
        while (ler.hasNextLine()) {
            String linha = ler.nextLine();
            //Ignorar linha de comentários (#) no ficheiro
            if (linha.startsWith("#")) continue;
            String[] elementos = linha.split("\\|");
            switch (elementos[0]) {
                case "PASSENGER":
                    //this.registPassageiro(elementos[1], LocalDate.parse(elementos[2]));
                    break;

                case "SERVICE":
                /*
                    if(!this.criarServico(elementos[1],elementos[2]))
                        break;
                    int id=Integer.parseInt(elementos[1]);
                    for (int i = 3; i < elementos.length-2; i+=2) {
                        this.adicionarSegmento(id,elementos[i+1],elementos[i], elementos[i+3],elementos[i+2] );
                    }

                    */
                    break;
            }
        }
    }

    //TODO: Completar
}
