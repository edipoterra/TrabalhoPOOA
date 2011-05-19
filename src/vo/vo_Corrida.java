package vo;
import java.util.Date;
/**
 * Essa classe possui os dados  do pais, posicao, piloto, e pontuacao.
 *
 * @author edipoterra
 *
 */
public class vo_Corrida {
    private int etapa;
    private String nome;
    private int codPais;
    private int codPista;
    private int num_voltas;
    private vo.vo_Pontuacao pontuacao;
    private Date diaCorrida;
    private vo_Posicao posicao;
    private int codPiloto;

    /**
     * retorna codigo da pista
     *
     * @return
     */
    public int getCodPista() {
        return codPista;
    }

    /**
     * Altera codigo da pista
     *
     * @param codPista
     */
    public void setCodPista(int codPista) {
        this.codPista = codPista;
    }

    /**
     * retorna numero voltas
     *
     * @return
     */
    public int getNum_voltas() {
        return num_voltas;
    }

    /**
     * Altera numero de voltas
     *
     * @param num_voltas
     */
    public void setNum_voltas(int num_voltas) {
        this.num_voltas = num_voltas;
    }

    /**
     * Retorna pontuacao
     *
     * @return
     */
    public vo_Pontuacao getPontuacao() {
        return pontuacao;
    }

    /**
     * Altera pontuacao
     *
     * @param pontuacao
     */
    public void setPontuacao(vo_Pontuacao pontuacao) {
        this.pontuacao = pontuacao;
    }

    /**
     * retorna cod Pais
     * @return 
     */
    public int getCodPais() {
        return codPais;
    }

    /**
     * Altera codigo do pais
     *
     * @param codPais
     */
    public void setCodPais(int codPais) {
        this.codPais = codPais;
    }

    /**
     * retorna posicao
     *
     * @return
     */
    public vo_Posicao getPosicao() {
        return posicao;
    }

    /**
     * altera posicao
     *
     * @param posicao
     */
    public void setPosicao(vo_Posicao posicao) {
        this.posicao = posicao;
    }

    /**
     * retorna dia da corrida
     *
     * @return
     */
    public Date getDiaCorrida() {
        return diaCorrida;
    }

    /**
     * Altera dia da corrida
     *
     * @param diaCorrida
     */
    public void setDiaCorrida(Date diaCorrida) {
        this.diaCorrida = diaCorrida;
    }

    /**
     * retorna etapa
     *
     * @return
     */
    public int getEtapa() {
        return etapa;
    }

    /**
     * altera etapa
     *
     * @param etapa
     */
    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    /**
     * retorna nome da prova
     *
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     * altera nome da prova
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo retorna codigo do piloto
     *
     * @return
     */
    public int getCodPiloto() {
        return codPiloto;
    }

    /**
     * Metodo altera o codigo do piloto
     *
     * @param codPiloto
     */
    public void setCodPiloto(int codPiloto) {
        this.codPiloto = codPiloto;
    }

}
