package vo;

/**
 * Esta classe possui a base para a herança das classes Piloto e Equipe. Acabam herdando
 * o nome e codigo dessa, mantendo o acesso a classe Pais.
 *
 * @author edipoterra
 *
 * 
 */
public class vo_Participante {
    private int codigo;
    private String nome;
    private int pais;

    /**
     * Metodo retorna o codigo do pais
     *
     * @return
     */
    public int getPais() {
        return pais;
    }

    /**
     * metodo altera o codigo do pais
     *
     * @param pais
     */
    public void setPais(int pais) {
        this.pais = pais;
    }

    /**
     * metodo retorna o codigo do participante
     *
     * @return
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * metodo altera o codigo do participante
     *
     * @param codigo
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Metodo retorna o nome do vo_Participante
     *
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     * Metodo altera o nome dos participantes
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }    
}