package atec.poo.cferro.app.exceptions;

public interface Labels {

    String NoPassegenrsRegistered = "Não existem passageiros registados na aplicação";

    /**
     * Retorna mensagem de erro quando um passageiro não existe
     *
     * @param id do passageiro
     * @return Mensagem formatada
     */
    static String noSuchPassegenr(int id) {
        return String.format("Passageiro com o id %d não existe.", id);
    }

    /**
     * Retorna mensagem de erro quando o número de dias para incrementar a data é inválido (<=0)
     * @param days
     * @return Mensagem formatada
     */
    static String invalidNumberOfDays(int days) {
        return String.format("Número de dias inválido (%d). Número tem que ser maior que zero.", days);
    }

    /**
     * Retorna mensagem de erro quando não existe a estação inserida pelo Utilizador
     * @param estacao
     * @return Mensagem formatada
     */
    static String noSuchEstacao(String estacao){
        return String.format("A estação %s não existe.",estacao);
    }

    /**
     * Retorna Menssagem de erro caso o serviço não exista
     * @param service
     * @return Mensagem formatada
     */
    static String noSuchService(int service){
        return String.format("Serviço %d não existe.",service);
    }

    /**
     * Retorna Mensagem de erro caso na pesquisa de passageiros não seja encontrado
     * nenhum passageiro que corresponda ao termo pesquisado
     * @param term Termo de pesquisa utilizado
     * @return Mensagem formatada
     */
    static String noPassengersFound(String term) {
        return "Pesquisar passageiros (por nome): Nenhum passageiro encontrado com o critério \"" + term + "\"";
    }
}
