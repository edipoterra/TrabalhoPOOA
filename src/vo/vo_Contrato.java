package vo;
import java.util.Date;
/**
 * A classe vo_Contrato possui a consistencia e toda a linkagem para fazer a conexão
 * entre piloto e equipe. No Banco de dados, essa classe não existe, portanto ela
 * fica implícita dentro da classe piloto, com o cadastro da equipe e as datas
 * correspondentes do contrato.
 *
 * @author edipoterra
 */
public class vo_Contrato {
    /**
     * Declaracao das datas usadas no contrato
     */
    private Date dataInicial;
    private Date dataFinal;

    /**
     * Metodo recupera os dados da data final
     *
     * @return
     */
    public Date getDataFinal() {
        return dataFinal;
    }

    /**
     * Metodo altera os dados da data final
     *
     * @param dataFinal
     */
    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    /**
     * Metodo recupera os dados da data inicial
     *
     * @return
     */
    public Date getDataInicial() {
        return dataInicial;
    }

    /**
     * Metodo altera os dados da data inicial
     *
     * @param dataInicial
     */
    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }
}
