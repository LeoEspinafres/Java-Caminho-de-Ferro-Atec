package atec.poo.cferro.app;
import atec.poo.cferro.app.main.Menu;
import atec.poo.cferro.core.CaminhoFerro;

import java.io.IOException;

import static atec.poo.ui.Dialogo.IO;


public class App {
    public static void main(String[] args) {
        CaminhoFerro cf = new CaminhoFerro();
        //Efetuar Import caso haja
        String dataFile=System.getProperty("import");
        if(dataFile!=null) {
            try {
                cf.importFile(dataFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Menu menu=new Menu(cf);
        menu.open();
    }
}
