package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import vo.vo_Escuderia;

/**
 * Classe faz os cadastros referentes a equipe (ou escuderia), gravando os dados
 * em banco, com a criacao das conexoes necessarias.
 *
 * @author edipoterra
 */

public class dao_Escuderia {
    /**
     * Declaracao da variavel responsavel pela criacao da conexao
     */
    private Connection conn;

    /**
     * Metodo construtor que inicializa a conexao com a mesma passada por parametro
     *
     * @param conn
     */
    public dao_Escuderia(Connection conn){
        this.conn = conn;
    }

    /**
     * Metodo construtor que inicializa a conexao com o processo padrao
     *
     * @throws exception_RegistroJaExisteException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public dao_Escuderia() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        conn = getConnection();
    }

    /**
     * Metodo cria a conexao com o banco de dados, recebe os dados da camada
     * de negocio e grava em banco. Apos essa etapa, fecha a conexao com o banco.
     *
     * @param equipe
     * @return
     * @throws exception_RegistroJaExisteException
     */
    public int insere(vo.vo_Escuderia equipe) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        conn = this.getConnection();
        int inseriu;

        if (equipe!=null){
            inseriu = equipe.getCodigo();
            PreparedStatement pstmt;

            try{
                pstmt = conn.prepareStatement("insert into Escuderia(cod_escuderia, nome_escuderia, cod_pais) values (?, ?, ?)");

                pstmt.setInt(1, equipe.getCodigo());
                pstmt.setString(2, equipe.getNome());
                pstmt.setInt(3, equipe.getPais());

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
     * Metodo faz a conexao com o banco de dados, grava as alteracoes feitas no banco,
     * e finalizando, fecha a conexao com o banco.
     *
     * @param equipe
     * @return
     * @throws exception_RegistroJaExisteException
     */
    public int altera(vo.vo_Escuderia equipe) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        conn = this.getConnection();
        int alterou;

        if (equipe!=null){
            alterou = equipe.getCodigo();
            PreparedStatement pstmt;

            try{
                pstmt = conn.prepareStatement("update escuderia set nome_escuderia = ?, cod_pais = ?  where cod_escuderia = ?");

                pstmt.setString(1, equipe.getNome());
                pstmt.setInt(2, equipe.getPais());
                pstmt.setInt(3, equipe.getCodigo());

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
            alterou = -1;
        }
        return alterou;

    }

    /**
     * Metodo faz a conexao com o banco de dados, executa a delecao dos dados
     * em banco de dados, seguindo as informacoes recebidas pela camada de negocios e
     * fecha a conexao.
     *
     * @param equipe
     * @return
     * @throws exception_RegistroJaExisteException
     */
    public int exclui(vo.vo_Escuderia equipe) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        conn = this.getConnection();
        int removeu;

        if (equipe!=null){
            removeu = equipe.getCodigo();
            PreparedStatement pstmt;

            try{
                pstmt = conn.prepareStatement("delete from escuderia where cod_escuderia =  ?");

                pstmt.setInt(1, equipe.getCodigo());

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
     * Metodo cria a conexao com o banco de dados, faz o select dos dados da tabela
     * e retorna uma matriz com esses dados da tabela. Caso nao consiga selecionar
     * ou ocorra algum problema, gera uma excecao que exporta. Apos o processo de selecao, ele
     * fecha a conexao.
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
     * Metodo faz a real conexao com o banco de dados, retornando uma excecao caso
     * ocorra algum erro, ou simplesmente retornando a propria conexao
     *
     * @return
     * @throws exception_RegistroJaExisteException
     */
    public Connection getConnection() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException {
        return dao_GeraConnection.getConexao();
    }

    public vo_Escuderia selecionaEquipe(vo_Escuderia equipe) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        conn = getConnection();
        PreparedStatement pstmt;
        ResultSet rs;
        try{
            pstmt = conn.prepareStatement("select cod_escuderia, nome_escuderia, cod_pais from escuderia where cod_escuderia = ?");
            pstmt.setInt(1, equipe.getCodigo());

            rs = pstmt.executeQuery();

            while(rs.next()){
                equipe.setCodigo(Integer.parseInt(rs.getString("cod_escuderia")));
                equipe.setNome(rs.getString("nome_escuderia"));
                equipe.setPais(Integer.parseInt(rs.getString("cod_pais")));
            }

            rs.close();
            conn.commit();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipe;
    }

    /**
     * Metodo faz a busca para a relacao de equipes
     *
     * @return
     * @throws exception_RegistroJaExisteException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public String[][] selectRelacaoEquipe() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        int tam = 0;
        conn = this.getConnection();
        PreparedStatement pstmt;
        ResultSet rs;
        ResultSet resSet;
        try{
            pstmt = conn.prepareStatement("select cod_escuderia, nome_escuderia, cod_pais from escuderia");

            ResultSet aux;
            aux = pstmt.executeQuery();

            while(aux.next()) {
                tam++;
            }

            String vetor[][] = new String[4][tam];

            int cont = 0;
            aux.close();

            pstmt = conn.prepareStatement("select cod_escuderia, nome_escuderia, cod_pais from escuderia");

            rs = pstmt.executeQuery();

            while(rs.next()){
                vetor[0][cont] = rs.getString("cod_escuderia");
                vetor[1][cont] = rs.getString("nome_escuderia");
                vetor[2][cont] = rs.getString("cod_pais");
                cont++;
            }
            rs.close();
            conn.commit();
            pstmt.close();

            for (int i = 0; i < vetor[0].length; i++){
                pstmt = conn.prepareStatement("select nome_pais from pais where cod_pais = ?");
                pstmt.setInt(1, Integer.parseInt(vetor[2][i]));
                rs = pstmt.executeQuery();
                while(rs.next()){
                    vetor[3][i] = rs.getString("nome_pais");
                }
                rs.close();
                conn.commit();
                pstmt.close();
            }

            return vetor;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}