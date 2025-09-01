package atec.poo.cferro.app.main;

import atec.poo.cferro.core.Bilheteira;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public interface Labels {

    //Título do Menu
    String MENU_PRINCIPAL = "-- MENU PRINCIPAL --";

    //Comandos do Menu
    String DoOpen="Carregar aplicação";
    String DoSave="Guardar aplicação";
    String DoIncrementDays="Incrementar dia(s)";
    String DoOpenPassageiros="Abrir menu de Passageiros";
    String DoOpenItinerarios="Abrir menu de Itinerarios";
    String DoOpenservicos="Abrir menu de Serviços e Estações";

    //Pedidos ao utilizador
    String ASK_FILE_NAME="Insira nome do ficheiro: ";
    String ASK_DIAS="Insira o número de dias a incrementar à data atual (>0): ";

    //Mensagem de retorno
    static String dataAtualAtualizada(int dias){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return String.format("Data Atualizada em %d dia(s). Data Atual: %s",dias, Bilheteira.TODAY.format(formato));
    }

    static String ficheiroCarregado(String nome){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return String.format("Ficheiro Carregado: %s. Data Atual: %s",nome,Bilheteira.TODAY.format(formato));
    }

    static String ficheiroSalvo(String nome){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return String.format("Ficheiro Salvo: %s. Data Atual: %s",nome,Bilheteira.TODAY.format(formato));
    }
}
