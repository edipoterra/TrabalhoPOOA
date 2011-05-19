package vo;

//essa classe so vai receber dados da GUI e passar para a GUI, nao interage com
//a camada DAO.

/**
 * Classe que simplesmente faz a conexão para gerar os dados para a corrida
 * ela vai possuir os dados de vo_Pontuacao e vo_Piloto para passar para a classe corrida
 * para conseguir fazer a implementação dessa budega.
 *
 * @author edipoterra
 */
public class vo_Posicao {
    /**
     * declaracao dos campos referente a posicao e pontuacao em uma corrida
     */
    private vo_Piloto piloto;
    private vo_Pontuacao pontuacao;
    private int posicao;

    /**
     * Metodo recupera uma posicao presente na variavel posicao
     * @return
     * 
     */
    public int getPosicao() {
        return posicao;
    }

    /**
     * altera o conteudo da posicao da variavel
     * @param posicao
     * 
     */
    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    /**
     * Metodo recupera os dados na variavel piloto
     * @return
     * 
     */
    public vo_Piloto getPiloto() {
        return piloto;
    }

    /**
     * metodo altera o conteudo de piloto
     * @param piloto
     * 
     */
    public void setPiloto(vo_Piloto piloto) {
        this.piloto = piloto;
    }

    /**
     * Metodo recupera o conteudo de pontuacao
     * @return
     * 
     */
    public vo_Pontuacao getPontuacao() {
        return pontuacao;
    }

    /**
     * Metodo altera os dados de pontuacao
     * @param pontuacao
     * 
     */
    public void setPontuacao(vo_Pontuacao pontuacao) {
        this.pontuacao = pontuacao;
    }

}
