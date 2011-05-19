package vo;

/**
 * A partir da posição que o piloto vai ficar na Corrida, o piloto vai receber uma
 * pontuação. Essa classe vai receber dados do piloto com a posicão.
 *
 * @author edipoterra
 */
public class vo_Pontuacao {
    private int posicao;
    private int pontos;

    /**
     * metodo recupera a quantidade de pontos
     * @return
     * 
     */
    public int getPontos() {
        return pontos;
    }

    /**
     * metodo altera a quantidade de pontos
     * @param pontos
     * 
     */
    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    /**
     * metodo recupera posicao do piloto
     * @return
     * 
     */
    public int getPosicao() {
        return posicao;
    }

    /**
     * metodo altera a posicao de um piloto
     * @param posicao
     * 
     */
    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    /**
     * os demais campos dessa classe serão pegos de outras classes correspondentes a esta relacao
     * Esta classe não vai ter conexão direta com o banco de dados, ela vai apenas passar os dados necessarios
     * para implementacao da pontuacao
     *
     * @param pos
     *
     */
    public void pontuacao(int pos){
        switch (pos){
            case 1:
                this.setPontos(25);
                break;
            case 2:
                this.setPontos(20);
                break;
            case 3:
                this.setPontos(15);
                break;
            case 4:
                this.setPontos(10);
                break;
            case 5:
                this.setPontos(8);
                break;
            case 6:
                this.setPontos(6);
                break;
            case 7:
                this.setPontos(5);
                break;
            case 8:
                this.setPontos(3);
                break;
            case 9:
                this.setPontos(2);
                break;
            case 10:
                this.setPontos(1);
                break;

            default:
                this.setPontos(0);
        }
    }
}
