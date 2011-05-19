package dao;

/**
 * Classe responsavel por criar as mensagens de excecao durante a conexão de banco e de erros
 * de conexoes.
 *
 * @author edipoterra
 *
 */

public class exception_RegistroJaExisteException extends Exception {
    /**
     * Declaracao de texto padrao para as mensagens de erro geradas pelas
     * exceptions.
     */
    private static String errorMessage = "Já existe registro com a chave fornecida";

    /**
     *  Metodo contrutor que faz a criacao de uma mensagem padrao com o texto definido na declaracao
     */
    public exception_RegistroJaExisteException() {
        super(errorMessage);
    }

    /**
     * Metodo construtor que gera uma mensagem com a definicao de mensagem de erro para os erros de SQL
     * e passando em que tabela esta o erro.
     * @param tabela
     */
    public exception_RegistroJaExisteException(String tabela) {
        super(errorMessage + " na tabela " + tabela);
    }

    /**
     * Metodo construtor que gera uma mensagem com a definicao do qual erro pode acontecer durante a conexao,
     * passando por parametro as mensagens e qual tabela esta relacionada.
     *
     * @param errorMessage
     * @param Tabela
     */
    public exception_RegistroJaExisteException(String errorMessage, String Tabela){
        super(errorMessage);
    }

    /**
     * Metodo contrutor que gera a causa do que pode ter ocorrido o erro.
     *
     * @param cause
     */
    public exception_RegistroJaExisteException(Throwable cause) {
        super(cause);
    }

    /**
     * Passa por parametro e tabela e verifica as causas do erro referente a certa tabela.
     *
     * @param tabela
     * @param cause
     */
    public exception_RegistroJaExisteException(String tabela, Throwable cause) {
        super(tabela, cause);
    }
}
