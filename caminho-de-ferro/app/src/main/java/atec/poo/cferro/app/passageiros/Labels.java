package atec.poo.cferro.app.passageiros;

import atec.poo.cferro.core.Bilheteira;

import java.time.format.DateTimeFormatter;

public interface Labels {
    String MENU_PASSAGEIROS = "MENU PASSAGEIROS";
    String REGISTAR_PASSAGEIRO = "Registar passageiro";
    String VER_PASSAGEIRO = "Ver passageiro";
    String LISTAR_PASSAGEIROS = "Listar passageiros (por nome)";
    String LISTAR_PASSAGEIROS_POR_VIAGENS = "Listar Passagiros (por valor viagens)";
    String LISTAR_PASSAGEIROS_IDADE = "Listar Passageiros (por idade)";
    String ALTERAR_NOME_PASSAGEIRO = "Alterar passageiro (nome)";
    String PESQUISAR_PASSAGEIROS = "Pesquisar passageiros";

    //Solicitar Dados
    String ASK_NOME_PASSAGEIRO = "Insira o nome do Passageiro: ";
    String ASK_ID_PASSAGEIRO = "Insira o id do Passageiro: ";
    String ASK_DATA_NASCIMENTO = "Insira a data de nascimento do Passageiro: ";
    String ASK_TERMO_PESQUISA = "Insira o termo de pesquisa: ";


    //Mensagens de retorno
    static String passageiroCriado(int id) {
        return String.format("Passageiro ID: %d criado com Sucesso.", id);
    }

    static String passageiroAlterado(int id, String nome) {
        return String.format("Passageiro ID: %d alterado com sucesso [%s].",id,nome);
    }

    static String listaPassageirosHeader(int numPassageiros) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return String.format("------- Lista de Passageiros [%d] | Data: %s -------", numPassageiros, Bilheteira.TODAY.format(formato));
    }

    static String listaPassageirosItem(int id, String nome, String dataNascimento, int idadeAnos, String categoria, int numItenerarios, double valorItenerarios) {
        return String.format("-> [%d] %s - %s (%d) - %s - %d itenerário(s) (%.2f €)", id, nome, dataNascimento, idadeAnos, categoria, numItenerarios, valorItenerarios);
    }

    static String showPassageiro(
            int id,
            String nome,
            String dataNascimento,
            int idadeAnos,
            String categoria,
            int numItenerarios,
            double valorItenerarios
    ) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String out = String.format("#### Ficha de Passageiro [%03d] - %s ####\n", id, Bilheteira.TODAY.format(formato));
        out += String.format("Nome: %s\n", nome);
        out += String.format("Data de Nascimento: %s (%d anos)\n", dataNascimento, idadeAnos);
        out += String.format("Categoria: %s\n", categoria);
        out += String.format("Nº de Itinerários: %d\n", numItenerarios);
        out += String.format("Valor Total Itinerários: %.2f €\n", valorItenerarios);
        out += "################################################";
        return out;
    }



}