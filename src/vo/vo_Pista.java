package vo;

/**
 * Classe herda dados atributos e parte do acesso de banco de dados da classe
 * participante. Assim como a classe piloto, esta possui a linkagem com a classe
 * Contrato e com a classe piloto indiretamente.
 *
 * @author edipoterra
 */
public class vo_Pista{
    private int codPista;
    private String nomePista;
    private int codPais;
    /**
     * Metodo retorna o codigo do pais
     * @return
     * 
     */
    public int getCodPais() {
        return codPais;
    }

    /**
     * metodo que altera o codigo do pais
     * @param codPais
     * 
     */
    public void setCodPais(int codPais) {
        this.codPais = codPais;
    }

    /**
     * Metodo recupera o codigo da pista
     * @return
     * 
     */
    public int getCodPista() {
        return codPista;
    }

    /**
     * Metodo altera o codigo da pista
     * @param codPista
     * 
     */
    public void setCodPista(int codPista) {
        this.codPista = codPista;
    }

    /**
     * metodo retorna o nome da pista
     * @return
     * 
     */
    public String getNomePista() {
        return nomePista;
    }

    /**
     * Metodo altera o nome da pista
     * @param nomePista
     * 
     */
    public void setNomePista(String nomePista) {
        this.nomePista = nomePista;
    }

}
