package atec.poo.cferro.app.servicos;

public interface Labels {
    String MENU_SERVICOS = "-- MENU SERVIÇOS E ESTAÇÕES --";
    String ShowEstacoes="Listar todas as Estações";
    String ShowTodosServicos = "Listar todos os Serviços";
    String ShowServicoNumero= "Imprimir detalhe de um Serviço";
    String ShowServicosFimEm="Lista de Serviços com fim numa Estação";
    String ShowServicosInicioEm="Lista de Serviços com início numa Estação";
    String ShowServicosServemEstacao="Lista de Serviços que servem uma Estação";
    String ShowSegmentosServico="Lista de Segmentos de um Serviço";

    String RequestNomeEstacao="Insira o nome da Estação: ";
    String RequestServiceID="Insira o ID do serviço: ";


    static String listaServicosHeader(int num){
        return String.format("---- Existem %d serviços na Lista ----",num);
    }

    static String listaServicosItem(int id,double preco,String c_partida,String h_partida,String c_chegada,String h_chegada){
        return String.format("-> Serviço #%d [€%.2f] %s (%s) >> %s (%s)",id,preco,c_partida,h_partida,c_chegada,h_chegada);
    }

    static String listaEstaçoesHeader(int num){
        return String.format("---- Existem %d Estações na Lista ----",num);
    }

    static String listaEstacoeItem(String nome){
        return String.format("-> %s",nome);
    }

    static String ServiceDetailsHeader(int id, double preco){
        return String.format("Serviço #%3d @ €%.2f",id,preco);
    }

    static String SegmentsDetailsHeader(int id, String from, String to, double preco, int duracao){
        return String.format("Serviço #%3d %s >> %s @ €%.2f | %d minutos",id,from,to,preco,duracao);
    }

    static String SegmentoItem(String hora, String estacao){
        return String.format("%s %s",hora,estacao);
    }

    static String SegmentoDetailItem(String from, String from_h, String to, String to_h,int minutos, double preco){
        return String.format("-> %s (%s) >> %s (%s) @ € %.2f | %d minutos ",from,from_h,to,to_h,preco,minutos);
    }
}
