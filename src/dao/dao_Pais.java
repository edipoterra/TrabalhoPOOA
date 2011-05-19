package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import vo.vo_Pais;

/**
 * Classe responsavel por fazer o cadastro principal de Paises, onde todo o resto do
 * programa vai trabalhar ao redor. Essa classe possui os processos de conexao com o banco de dados,
 * grava os dados referente ao pais em banco, altera e deleta esses mesmos dados.
 *
 * @author edipoterra
 * 
 */
public class dao_Pais{

    /**
     * Declaracao da variavel que faz o controle e criacao do banco de dados, fazendo a conexao
     * do programa com o banco.
     *
     */
    private Connection conn;
    private vo_Pais pais;

    /**
     * Metodo construtor da classe dao_Pais, faz a intancia da conexao e do pais, vindo de
     * vo_Pais
     *
     * @param conn
     * @param pais
     */
    public dao_Pais(Connection conn, vo_Pais pais) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException {
        this.conn = conn;
        this.pais = pais;
    }

    /**
     * Construtor default da classe dao_Pais
     */
    public dao_Pais() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException {
        conn = getConnection();
    }

    /**
     * Contrutor de dao_Pais, que recebe a conexao por parametro
     *
     * @param conn
     * @throws exception_RegistroJaExisteException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public dao_Pais(Connection conn) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        this.conn = conn;
        conn = getConnection();
    }

    /**
     * Metodo faz a busca e retorna o objeto pais.
     * @return pais
     */
    public vo_Pais getPais() {
        return pais;
    }

    /**
     * Metodo altera os valores, ou o objeto completo de pais.
     * @param pais
     */
    public void setPais(vo_Pais pais) {
        this.pais = pais;
    }


    /**
     * Metodo faz a criacao da conexao, recebe os dados da camada de negocio e
     * grava no banco. Apos isso, ele fecha a conexao com o banco.
     * Caso nao puder fazer a insercao, o metodo retorna uma excecao de erro.
     *
     * @param pais
     * @return
     * @throws exception_RegistroJaExisteException
     */
    public int insere(vo_Pais pais) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        conn = getConnection();
        int inseriu;

        if (pais!=null){
            inseriu = pais.getCodigo();
            PreparedStatement pstmt;

            try{
                pstmt = conn.prepareStatement("insert into pais(cod_pais, nome_pais) values (?, ?)");

                pstmt.setInt(1, pais.getCodigo());
                pstmt.setString(2, pais.getNome());

                pstmt.execute();
                conn.commit();
                pstmt.close();
                
            } catch (SQLException e) {
                if(e.getErrorCode() == -104) {
                    throw new exception_RegistroJaExisteException("pais");
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
     * Metodo faz a alteracao de um pais, simplesmente alterando o nome do pais.
     * Para fazer isso, e criado uma conexao, executado os comandos para alteracao e
     * fecha a conexao.
     *
     * @param pais
     * @return
     * @throws exception_RegistroJaExisteException
     */
    public int altera(vo_Pais pais) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        conn = getConnection();
        int alterou;

        if (pais!=null){
            alterou = pais.getCodigo();
            PreparedStatement pstmt;

            try{
                pstmt = conn.prepareStatement("update pais set nome_pais = ? where cod_pais = ?");

                pstmt.setString(1, pais.getNome());
                pstmt.setInt(2, pais.getCodigo());

                pstmt.execute();
                conn.commit();
                pstmt.close();

            } catch (SQLException e) {
                if(e.getErrorCode() == -104) {
                    throw new exception_RegistroJaExisteException("Pais");
                    
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
     * Metodo de exclusao de pais. O metodo recebe os dados da camada de negocio,
     * cria uma conexao, faz a exclusao do pais referente ao pais e fecha a conexao.
     *
     * @param pais
     * @return
     * @throws exception_RegistroJaExisteException
     *
     */
    public int exclui(vo_Pais pais) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        conn = getConnection();
        int removeu;

        if (pais!=null){
            removeu = pais.getCodigo();
            PreparedStatement pstmt;

            try{
                pstmt = conn.prepareStatement("delete from pais where cod_pais =  ?");

                pstmt.setInt(1, pais.getCodigo());

                pstmt.execute();
                conn.commit();
                pstmt.close();

            } catch (SQLException e) {
                if(e.getErrorCode() == -104) {
                    throw new dao.exception_RegistroJaExisteException("pais");
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
     * Metodo faz a consulta dos dados e retorna os dados referente ao pais passado por parametro
     * @param pais
     * @return vo.vo_Pais
     * @throws exception_RegistroJaExisteException
     */
    public vo_Pais selecionaPais(vo_Pais pais) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        conn = getConnection();
        PreparedStatement pstmt;
        ResultSet rs;
        try{
            pstmt = conn.prepareStatement("select cod_pais, nome_pais from pais where cod_pais = ?");
            pstmt.setInt(1, pais.getCodigo());

            rs = pstmt.executeQuery();

            while(rs.next()){
                pais.setCodigo(Integer.parseInt(rs.getString("cod_pais")));
                pais.setNome(rs.getString("nome_pais"));
            }
            rs.close();
            conn.commit();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pais;
    }

    /**
     * Metodo que faz a selecao de indices para encontrar o maior codigo de pais
     *
     * @return
     * @throws exception_RegistroJaExisteException
     */
    public int selecionaIndice() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        conn = getConnection();
        int indice = 0;
        PreparedStatement pstmt;
        ResultSet rs;
        try{
            pstmt = conn.prepareStatement("select max(*) from pais");

            rs = pstmt.executeQuery();

            while(rs.next()){
                pais.setNome(rs.getString("nome_pais"));
                indice = Integer.parseInt(rs.getString("cod_pais"));
            }
            rs.close();
            conn.commit();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        indice++;
        return indice;
    }

    /**
     * Metodo faz a criacao da conexao e retorna a mesma. Verifica se o banco esta
     * rodando e caso ocorra algum erro, retorna uma excecao.
     *
     * @return
     * @throws exception_RegistroJaExisteException
     */
    public Connection getConnection() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException {
        return dao_GeraConnection.getConexao();
    }
}