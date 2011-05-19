package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import vo.vo_Pista;
/**
 * Classe faz o cadastramento dos dados referentes as pistas, gravando os dados em banco,
 * na tabela dao_Pista.
 * @author edipoterra
 */
public class dao_Pista {
    /*
     * cod_pista
     * nome_pista
     * cod_pais
     */

    /**
     * Cria variavel que controla e instancia a conexao para acesso ao banco.
     */
    private Connection conn;

    /**
     * Metodo construtor que inicia a conexao coms os dados vindos por parametro
     *
     * @param conn
     */
    public dao_Pista(Connection conn){
        this.conn = conn;
    }

    /**
     * Metodo construtor que inicia a conexao com o processo padrao de conexoes
     *
     * @throws exception_RegistroJaExisteException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public dao_Pista() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        conn = getConnection();
    }

    /**
     * Metodo faz insercao de dados na tabela dao_Pista, criando a conexao, inserindo os dados
     * referentes a esta etapa e fechando a conexao para manter a integridade da conexao.
     *
     * @param pista
     * @return
     * @throws exception_RegistroJaExisteException
     */
    public int insere(vo.vo_Pista pista) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        conn = this.getConnection();
        int inseriu;

        if (pista!=null){
            inseriu = pista.getCodPista();
            PreparedStatement pstmt;

            try{
                pstmt = conn.prepareStatement("insert into Pista(cod_pista, nome_pista, cod_pais) values (?, ?, ?)");

                pstmt.setInt(1, pista.getCodPista());
                pstmt.setString(2, pista.getNomePista());
                pstmt.setInt(3, pista.getCodPais());

                pstmt.execute();
                conn.commit();
                pstmt.close();

            } catch (SQLException e) {
                if(e.getErrorCode() == -104) {
                    throw new exception_RegistroJaExisteException("Escuderia");
                }
                else{
                    e.printStackTrace();
                }
            }
        }
        else{
            inseriu = -1;
        }
        return inseriu;
     }

    /**
     * Metodo faz a conexao e alteracao dos dados referentes a tabela pista, com os dados
     * passados pelo parametro do objeto pista. Este metodo faz a criacao da conexao, executa
     * a alteracao e fecha a conexao.
     *
     * @param pista
     * @return
     * @throws exception_RegistroJaExisteException
     */
     public int altera(vo.vo_Pista pista) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        conn = this.getConnection();
        int alterou;

        if (pista!=null){
            alterou = pista.getCodPista();
            PreparedStatement pstmt;

            try{
                pstmt = conn.prepareStatement("update pista set nome_pista = ?, cod_pais = ?  where cod_pista = ?");

                pstmt.setString(1, pista.getNomePista());
                pstmt.setInt(2, pista.getCodPais());
                pstmt.setInt(3, pista.getCodPista());

                pstmt.execute();
                conn.commit();
                pstmt.close();

            } catch (SQLException e) {
                if(e.getErrorCode() == -104) {
                    throw new exception_RegistroJaExisteException("pista");

                }
                else{
                    e.printStackTrace();
                }
            }
        }
        else{
            alterou = -1;
        }
        return alterou;
     }

     /**
      * Metodo faz exclusao dos dados referentes a pista, tendo como base o proprio
      * codigo de acesso. Esse metodo cria a conexao, deleta os dados e fecha a conexao.
      *
      * @param pista
      * @return
      * @throws exception_RegistroJaExisteException
      */
     public int exclui(vo.vo_Pista pista) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        conn = this.getConnection();
        int removeu;

        if (pista!=null){
            removeu = pista.getCodPista();
            PreparedStatement pstmt;

            try{
                pstmt = conn.prepareStatement("delete from pista where cod_pista =  ?");

                pstmt.setInt(1, pista.getCodPista());

                pstmt.execute();
                conn.commit();
                pstmt.close();

            } catch (SQLException e) {
                if(e.getErrorCode() == -104) {
                    throw new dao.exception_RegistroJaExisteException("Escuderia");
                }
                else{
                    removeu = -1;
                    e.printStackTrace();
                }
            }
        }
        else{
            removeu = -1;
        }
        return removeu;
     }

     /**
      * Metodo simplesmente gera a conexao e caso nao conseguir retorna uma
      * excessao avisando no que nao conseguiu se conectar, dependendo do caso pode
      * acusar problema do banco, do driver referente ao banco.
      *
      * @return
      * @throws exception_RegistroJaExisteException
      */
     public Connection getConnection() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException {
        return dao_GeraConnection.getConexao();
    }

     /**
      * Metodo faz um select de todos os dados cadastrados na tabela pais e exporta
      * para uma matriz com o codigo e com a sua respectiva descricao.
      * Ele cria a conexao, executa o select da melhor forma, finaliza e fecha a conexao e retorna
      * a matriz com dados ou retorna null.
      *
      * @return
      * @throws exception_RegistroJaExisteException
      */
    public String[][] select() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        int tam = 0;

        conn = this.getConnection();
        PreparedStatement pstmt;
        ResultSet rs;
        try{
            pstmt = conn.prepareStatement("select cod_pais, nome_pais from pais");

            ResultSet aux;
            aux = pstmt.executeQuery();

            while(aux.next()) {
                tam++;
            }
            String vetor[][] = new String[2][tam];

            int cont = 0;
            aux.close();

            pstmt = conn.prepareStatement("select cod_pais, nome_pais from pais");
            rs = pstmt.executeQuery();

            while(rs.next()){
                vetor[0][cont] = rs.getString("cod_pais");
                vetor[1][cont] = rs.getString("nome_pais");
                cont++;
            }

            rs.close();
            conn.commit();
            pstmt.close();
            return vetor;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * metodo recebe um codigo da pista e faz a consulta e manda de volta para a camada
     * de interface grafica 
     *
     * @param pista
     * @return pista
     * @throws exception_RegistroJaExisteException
     */
    public vo_Pista selecionaPista(vo_Pista pista) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        conn = this.getConnection();


        PreparedStatement pstmt;
        ResultSet rs;
        try{
            pstmt = conn.prepareStatement("select cod_pista, nome_pista, cod_pais from pista where cod_pista = ?");
            pstmt.setInt(1, pista.getCodPista());

            rs = pstmt.executeQuery();

            while(rs.next()){
                pista.setCodPista(Integer.parseInt(rs.getString("cod_pista")));
                pista.setNomePista(rs.getString("nome_pista"));
                pista.setCodPais(Integer.parseInt(rs.getString("cod_pais")));
            }
            
            rs.close();
            conn.commit();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pista;
    }
}
