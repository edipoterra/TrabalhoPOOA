package vo;

/**
 * Esta classe herda atributos e alguns métodos da classe vo_Participante, possui seus
 * dados específicos para os Pilotos, possui acesso ao contrato e os metodos de acesso
 * e gravação em banco.
 *
 * @author edipoterra
 *
 */
public class vo_Piloto extends vo_Participante{
    private vo_Contrato contrato;
    private int equipe;
    private int pais;

    /**
     * Retorna a equipe
     *
     * @return
     */
    public int getEquipe() {
        return equipe;
    }

    /**
     * Altera o codigo da equipe
     *
     * @param equipe
     */
    public void setEquipe(int equipe) {
        this.equipe = equipe;
    }

    /**
     * Retorna o contrato com a equipe
     *
     * @return
     */
    public vo_Contrato getContrato() {
        return contrato;
    }

    /**
     * Altera o contrato com a equipe
     *
     * @param contrato
     */
    public void setContrato(vo_Contrato contrato) {
        this.contrato = contrato;
    }

}