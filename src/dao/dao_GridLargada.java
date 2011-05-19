package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import vo.vo_Corrida;

/**
 * Classe recebe os dados da camada de negocio, verifica os dados e grava em banco
 * os dados. Essa classe possui uma chave composta, portanto necessita dos dados dessas
 * duas tabelas.
 * Essa classe cria, conexao com o banco, executa os comandos e fecha a conexao.
 *
 * @author edipoterra
 */
public class dao_GridLargada {

    /**
     * Declaracao da variavel que gera a conexao com o banco de dados.
     *
     */
    private Connection conn;

    /**
     * Metodo default da classe de gridLargada
     *
     * @throws exception_RegistroJaExisteException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public dao_GridLargada() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        conn = getConnection();
    }

    public dao_GridLargada(Connection conn){
        this.conn = conn;
    }

    /**
     * Metodo faz a conexao com o banco de dados, pega os dados que recebeu da
     * camada de negocio, faz a insercao com esses dados e fecha a conexao.
     * 
     * @param corrida
     * @return
     * @throws exception_RegistroJaExisteException
     */
    public int insere(vo.vo_Corrida corrida) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        conn = this.getConnection();
        int inseriu;

        if (corrida!=null){
            inseriu = corrida.getEtapa();
            PreparedStatement pstmt;

            try{
                pstmt = conn.prepareStatement("insert into gridlargada (cod_prova, cod_piloto, cod_pista, posicao) values (?, ?, ?, ?)");

                pstmt.setInt(1, corrida.getEtapa());
                pstmt.setInt(2, corrida.getCodPiloto());
                pstmt.setInt(3, corrida.getCodPista());
                pstmt.setInt(4, corrida.getPosicao().getPosicao());

                pstmt.execute();
                conn.commit();
                pstmt.close();

            } catch (SQLException e) {
                if(e.getErrorCode() == -104) {
                    throw new exception_RegistroJaExisteException("Piloto");
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
     * Metodo faz a conexao com o banco de dados, com os dados que recebeu da camada
     * de negocio, executa a alteracao e fecha a conexao com o banco
     * 
     * @param corrida
     * @return
     * @throws exception_RegistroJaExisteException
     */
    public int altera(vo.vo_Corrida corrida) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        conn = this.getConnection();
        int alterou;

        if (corrida!=null){
            alterou = corrida.getEtapa();
            PreparedStatement pstmt;

            try{
                pstmt = conn.prepareStatement("update gridlargada set cod_pista = ?, posicao = ? where cod_prova = ? and cod_piloto = ?");

                pstmt.setInt(1, corrida.getCodPista());
                pstmt.setInt(2, corrida.getPosicao().getPosicao());
                pstmt.setInt(3, corrida.getEtapa());
                pstmt.setInt(4, corrida.getCodPiloto());

                pstmt.execute();
                conn.commit();
                pstmt.close();

            } catch (SQLException e) {
                if(e.getErrorCode() == -104) {
                    throw new exception_RegistroJaExisteException("Piloto");
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
     * Metodo faz a conexao com o banco de dados, faz a exclusao com os dados que
     * recebeu da camada de negocio e fecha a conexao com o banco.
     *
     * @param corrida
     * @return
     * @throws exception_RegistroJaExisteException
     */
    public int exclui(vo.vo_Corrida corrida) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        conn = this.getConnection();
        int excluiu;

        if (corrida!=null){
            excluiu = corrida.getEtapa();
            PreparedStatement pstmt;

            try{
                pstmt = conn.prepareStatement("delete from gridlargada where cod_prova = ? and cod_piloto = ?");

                pstmt.setInt(1, corrida.getEtapa());
                pstmt.setInt(2, corrida.getCodPiloto());

                pstmt.execute();
                conn.commit();
                pstmt.close();

            } catch (SQLException e) {
                if(e.getErrorCode() == -104) {
                    throw new exception_RegistroJaExisteException("Piloto");
                }
                else{
                    e.printStackTrace();
                }
            }
        }
        else{
            excluiu = -1;
        }
        return excluiu;
    }

    /**
     * Metodo faz a conexao com o banco, faz um SELECT com os dados da tabela
     * prova e retorna uma matriz com esses valores gerados pela consulta. Apos
     * faz o fechamento da conexao
     *
     * @return
     * @throws exception_RegistroJaExisteException
     */
    public String[][] selectProva() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        int tam = 0;

        conn = this.getConnection();
        PreparedStatement pstmt;
        ResultSet rs;
        try{
            pstmt = conn.prepareStatement("select cod_prova, nome_prova from prova");

            ResultSet aux;
            aux = pstmt.executeQuery();

            while(aux.next()) {
                tam++;
            }
            String vetor[][] = new String[2][tam];

            int cont = 0;
            aux.close();

            pstmt = conn.prepareStatement("select cod_prova, nome_prova from prova");
            rs = pstmt.executeQuery();

            while(rs.next()){
                vetor[0][cont] = rs.getString("cod_prova");
                vetor[1][cont] = rs.getString("nome_prova");
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
     * Metodo faz a conexao com o banco, faz um SELECT com os dados da tabela
     * pista e retorna uma matriz com esses valores gerados pela consulta. Apos
     * faz o fechamento da conexao
     *
     * @return
     * @throws exception_RegistroJaExisteException
     */
    public String[][] selectPista() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        int tam = 0;

        conn = this.getConnection();
        PreparedStatement pstmt;
        ResultSet rs;
        try{
            pstmt = conn.prepareStatement("select cod_pista, nome_pista from pista");

            ResultSet aux;
            aux = pstmt.executeQuery();

            while(aux.next()) {
                tam++;
            }
            String vetor[][] = new String[2][tam];

            int cont = 0;
            aux.close();

            pstmt = conn.prepareStatement("select cod_pista, nome_pista from pista");
            rs = pstmt.executeQuery();

            while(rs.next()){
                vetor[0][cont] = rs.getString("cod_pista");
                vetor[1][cont] = rs.getString("nome_pista");
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
     * Metodo faz a conexao com o banco, faz um SELECT com os dados da tabela
     * piloto e retorna uma matriz com esses valores gerados pela consulta. Apos
     * faz o fechamento da conexao
     *
     * @return
     * @throws exception_RegistroJaExisteException
     */
    public String[][] selectPiloto() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        int tam = 0;

        conn = this.getConnection();
        PreparedStatement pstmt;
        ResultSet rs;
        try{
            pstmt = conn.prepareStatement("select cod_piloto, nome_piloto from piloto");

            ResultSet aux;
            aux = pstmt.executeQuery();

            while(aux.next()) {
                tam++;
            }
            String vetor[][] = new String[2][tam];

            int cont = 0;
            aux.close();

            pstmt = conn.prepareStatement("select cod_piloto, nome_piloto from piloto");
            rs = pstmt.executeQuery();

            while(rs.next()){
                vetor[0][cont] = rs.getString("cod_piloto");
                vetor[1][cont] = rs.getString("nome_piloto");
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
     * Metodo faz a conexao real com o banco e retorna essa conexao.
     * Caso nao conseguir se conectar, retorna uma excecao.
     *
     * @return
     * @throws exception_RegistroJaExisteException
     */
    public Connection getConnection() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException {
        return dao.dao_GeraConnection.getConexao();
    }

    /**
     * Metodo que faz a selecao do grid de largada
     *
     * @param corrida
     * @return
     * @throws exception_RegistroJaExisteException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public String[][] selectLargada(vo.vo_Corrida corrida) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException {
        int tam = 0;

        conn = this.getConnection();
        PreparedStatement pstmt;
        ResultSet rs;
        try{
            pstmt = conn.prepareStatement("select cod_piloto, posicao from gridlargada where cod_prova = ?");

            pstmt.setInt(1, corrida.getEtapa());

            ResultSet aux;
            aux = pstmt.executeQuery();

            while(aux.next()) {
                tam++;
            }
            String vetor[][] = new String[3][tam];

            int cont = 0;
            aux.close();

            pstmt = conn.prepareStatement("select cod_piloto, posicao from gridlargada where cod_prova = ?");

            pstmt.setInt(1, corrida.getEtapa());

            rs = pstmt.executeQuery();

            while(rs.next()){
                vetor[0][cont] = rs.getString("cod_piloto");
                vetor[2][cont] = rs.getString("posicao");
                cont++;
            }
            rs.close();
            conn.commit();
            pstmt.close();

            for (int i = 0; i < vetor[0].length; i++){
                pstmt = conn.prepareStatement("select nome_piloto from piloto where cod_piloto = ?");
                pstmt.setInt(1, Integer.parseInt(vetor[0][i]));
                rs = pstmt.executeQuery();

                while(rs.next()){
                    vetor[1][i] = rs.getString("nome_piloto");
                }
            }
            return vetor;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
