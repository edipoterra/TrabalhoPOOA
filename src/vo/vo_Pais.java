package vo;

/**
 * Classe que possui os atributos e metodos para fazer o acesso a banco de dados
 * e cria as bases para acesso de outras classes.
 * Essa classe vai ser acessada pelas classes Participantes e Corridas.
 * Possui os campos nomes e código. Esse ultimo é para acesso a banco.
 *
 * @author edipoterra
 *
 */


public class vo_Pais{
    private int codigo;
    private String nome;

    /**
     * Metodo retorna o codigo do pais
     *
     * @return
     */
    public int getCodigo(){
        return this.codigo;
    }

    /**
     * Metodo retorna o nome do pais
     *
     * @return
     */
    public String getNome(){
        return this.nome;
    }

    /**
     * Metodo altera o codigo do pais
     *
     * @param codigo
     */
    public void setCodigo(int codigo){
        this.codigo = codigo;
    }

    /**
     * Metodo altera o nome do pais
     *
     * @param nome
     */
    public void setNome(String nome){
        this.nome = nome;
    }

}
