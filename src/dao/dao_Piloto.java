package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import vo.vo_Contrato;
import vo.vo_Piloto;

/**
 * Classe responsavel por manipular os dados referentes ao cadastro de pilotos, e
 * faz o merge com os dados referentes ao contrato ao mesmo tempo.
 *
 * @author edipoterra
 */
public class dao_Piloto {
    /**
     *
     * Faz a criacao da variavel responsavel por criar e gerenciar a conexao com o banco de dados
     */
    private Connection conn;

    /**
     * Contrutor de piloto que inicia a conexao com o processo padrao
     *
     * @throws exception_RegistroJaExisteException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public dao_Piloto() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        conn = getConnection();
    }

    /**
     * Contrutor de piloto que recebe a conexao por parametro
     *
     * @param conn
     */
    public dao_Piloto(Connection conn){
        this.conn = conn;
    }

    /**
     * Metodo faz a criacao e gerencia os dados e faz a execucao da insercao dos dados
     * do piloto, assim como os dados do contrato do mesmo com alguma equipe. Apos isso
     * gera uma conexao, grava os dados em banco e fecha a conexao.
     *
     * @param piloto
     * @return
     * @throws exception_RegistroJaExisteException
     */
    public int insere(vo.vo_Piloto piloto) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        conn = this.getConnection();
        int inseriu;

        if (piloto!=null){
            inseriu = piloto.getCodigo();
            PreparedStatement pstmt;

            try{
                pstmt = conn.prepareStatement("insert into piloto(cod_piloto, nome_piloto, cod_pais, cod_equipe, dt_ini_contrato, dt_fim_contrato) values (?, ?, ?, ?, ?, ?)");

                pstmt.setInt(1, piloto.getCodigo());
                pstmt.setString(2, piloto.getNome());
                pstmt.setInt(3, piloto.getPais());
                pstmt.setInt(4, piloto.getEquipe());
                java.sql.Date dtInicial = new java.sql.Date(piloto.getContrato().getDataInicial().getYear(), piloto.getContrato().getDataInicial().getMonth(), piloto.getContrato().getDataInicial().getDate());
                java.sql.Date dtFinal = new java.sql.Date(piloto.getContrato().getDataFinal().getYear(), piloto.getContrato().getDataFinal().getMonth(), piloto.getContrato().getDataFinal().getDate());
                pstmt.setDate(5, dtInicial);
                pstmt.setDate(6, dtFinal);

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
     * Metodo faz a criacao da conexao, executa a alteracao se possivel e e finaliza a conexao.
     * Nao trabalhando apenas com o cadastro de piloto assim como os dados do contrato do mesmo
     *
     * @param piloto
     * @return
     * @throws exception_RegistroJaExisteException
     */
    public int altera(vo.vo_Piloto piloto) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        conn = this.getConnection();
        int alterou;

        if (piloto!=null){
            alterou = piloto.getCodigo();
            PreparedStatement pstmt;

            try{
                pstmt = conn.prepareStatement("update piloto set nome_piloto = ?, cod_pais = ?, cod_equipe = ?, dt_ini_contrato = ?, dt_fim_contrato = ? where cod_piloto = ?");
                
                pstmt.setString(1, piloto.getNome());
                pstmt.setInt(2, piloto.getPais());
                pstmt.setInt(3, piloto.getEquipe());
                
                java.sql.Date dtInicial = java.sql.Date.valueOf("1900-01-01");
                dtInicial.setTime(piloto.getContrato().getDataInicial().getTime());
                
                java.sql.Date dtFinal = java.sql.Date.valueOf("1900-01-01");
                dtFinal.setTime(piloto.getContrato().getDataFinal().getTime());
                pstmt.setDate(4, dtInicial);
                pstmt.setDate(5, dtFinal);
                pstmt.setInt(6, piloto.getCodigo());
                
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
     * Metodo responsavel por fazer a exclusao de todos os dados referentes
     * ao piloto.
     *
     * @param piloto
     * @return
     * @throws exception_RegistroJaExisteException
     */
    public int exclui(vo.vo_Piloto piloto) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        conn = this.getConnection();
        int removeu;

        if (piloto!=null){
            removeu = piloto.getCodigo();
            PreparedStatement pstmt;

            try{
                pstmt = conn.prepareStatement("delete from piloto where cod_piloto =  ?");

                pstmt.setInt(1, piloto.getCodigo());

                pstmt.execute();
                conn.commit();
                pstmt.close();

            } catch (SQLException e) {
                if(e.getErrorCode() == -104) {
                    throw new dao.exception_RegistroJaExisteException("Piloto");
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
     * Metodo faz a criacao de uma matriz com os dados de um select de equipes (ou escuderia)
     * com os dados cadastrados na tabela.
     * @return
     * @throws exception_RegistroJaExisteException
     */
    public String[][] selectEquipe() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        int tam = 0;

        conn = this.getConnection();
        PreparedStatement pstmt;
        ResultSet rs;
        try{
            pstmt = conn.prepareStatement("select cod_escuderia, nome_escuderia from escuderia");

            ResultSet aux;
            aux = pstmt.executeQuery();

            while(aux.next()) {
                tam++;
            }
            String vetor[][] = new String[2][tam];

            int cont = 0;
            aux.close();

            pstmt = conn.prepareStatement("select cod_escuderia, nome_escuderia from escuderia");
            rs = pstmt.executeQuery();

            while(rs.next()){
                vetor[0][cont] = rs.getString("cod_escuderia");
                vetor[1][cont] = rs.getString("nome_escuderia");
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
     * Metodo faz select com codigo e nome do pais, exporta uma matriz com os dados
     * que e usada para popular os comboboxes
     *
     * @return
     * @throws exception_RegistroJaExisteException
     */
    public String[][] selectPais() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
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
     * Faz a selecao de pilotos, gerando todos os dados necessarios para o processo de selecao do mesmo
     *
     * @param piloto
     * @return
     * @throws exception_RegistroJaExisteException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public vo_Piloto selecionaPiloto(vo_Piloto piloto) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        conn = this.getConnection();

        PreparedStatement pstmt;
        ResultSet rs;
        try{
            pstmt = conn.prepareStatement("select cod_piloto, nome_piloto, cod_pais, cod_equipe, dt_ini_contrato, dt_fim_contrato from piloto where cod_piloto = ?");
            pstmt.setInt(1, piloto.getCodigo());

            rs = pstmt.executeQuery();

            while(rs.next()){
                piloto.setCodigo(Integer.parseInt(rs.getString("cod_piloto")));
                piloto.setNome(rs.getString("nome_piloto"));
                piloto.setPais(Integer.parseInt(rs.getString("cod_pais")));
                piloto.setEquipe(Integer.parseInt(rs.getString("cod_equipe")));
                vo_Contrato contrato = new vo_Contrato();
                String dataAux;

                dataAux = (rs.getDate("dt_ini_contrato")).toString();
                Date dataAux2 = new Date();
                dataAux2 = java.sql.Date.valueOf(dataAux);
                contrato.setDataInicial(dataAux2);

                dataAux = null;
                dataAux = (rs.getDate("dt_fim_contrato")).toString();
                dataAux2 = null;
                dataAux2 = java.sql.Date.valueOf(dataAux);
                contrato.setDataFinal(rs.getDate("dt_fim_contrato"));
                
                piloto.setContrato(contrato);
                
            }

            rs.close();
            conn.commit();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return piloto;
    }

    /**
     * Metodo que seleciona os dados do piloto para tela de relacao com todos os pilotos
     *
     * @return
     * @throws exception_RegistroJaExisteException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public String[][] selectRelacaoPiloto() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        int tam = 0;
        conn = this.getConnection();
        PreparedStatement pstmt;
        ResultSet rs;
        try{
            pstmt = conn.prepareStatement("select cod_piloto, nome_piloto, cod_pais, cod_equipe from piloto");

            ResultSet aux;
            aux = pstmt.executeQuery();

            while(aux.next()) {
                tam++;
            }
            
            String vetor[][] = new String[6][tam];

            int cont = 0;
            aux.close();

            pstmt = conn.prepareStatement("select cod_piloto, nome_piloto, cod_pais, cod_equipe from piloto");

            rs = pstmt.executeQuery();

            while(rs.next()){
                vetor[0][cont] = rs.getString("cod_piloto");
                vetor[1][cont] = rs.getString("nome_piloto");
                vetor[2][cont] = rs.getString("cod_pais");
                vetor[4][cont] = rs.getString("cod_equipe");
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

                pstmt = conn.prepareStatement("select nome_escuderia from escuderia where cod_escuderia = ?");
                pstmt.setInt(1, Integer.parseInt(vetor[4][i]));
                rs = pstmt.executeQuery();

                while(rs.next()){
                    vetor[5][i] = rs.getString("nome_escuderia");
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

    /**
     * metodo cria a conexao com o banco, cria excecao caso ocorra algum erro e retorna a conexao
     *
     * @return
     * @throws exception_RegistroJaExisteException
     */
    public Connection getConnection() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException {
        return dao_GeraConnection.getConexao();
    }

}
