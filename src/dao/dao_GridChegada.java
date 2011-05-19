package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import vo.vo_Corrida;
/**
 * Classe faz a conexao com o banco de dados, usa os dados recebidos pela camada de
 * negocios e grava em banco.
 *
 * @author edipoterra
 */
public class dao_GridChegada {
    /**
     * Declaracao da variavel de controle da conexao com o banco
     */

    private Connection conn;
    /**
     * Metodo construtor que faz a criacao da conexao com o banco
     *
     * @throws exception_RegistroJaExisteException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public dao_GridChegada() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        conn = getConnection();
    }

    /**
     * Metodo construtor que faz a criacao da conexao com o parametro 
     *
     * @param conn
     */
    public dao_GridChegada(Connection conn){
        this.conn = conn;
    }

    /**
     * Metodo faz a conexao com o banco, faz um insere os dados na tabela
     * Apos faz o fechamento da conexao
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
                pstmt = conn.prepareStatement("insert into gridchegada (cod_prova, cod_piloto, cod_pista, posicao, pontuacao) values (?, ?, ?, ?, ?)");

                pstmt.setInt(1, corrida.getEtapa());
                pstmt.setInt(2, corrida.getCodPiloto());
                pstmt.setInt(3, corrida.getCodPista());
                pstmt.setInt(4, corrida.getPosicao().getPosicao());
                pstmt.setInt(5, corrida.getPontuacao().getPontos());

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
     * Metodo faz a conexao com o banco de dados, gerando essa conexao,
     * executa a alteracao referente ao grid de chegada e fecha a conexao com o banco.
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
                pstmt = conn.prepareStatement("update gridchegada set cod_pista = ?, posicao = ?, pontuacao = ? where cod_prova = ? and cod_piloto = ?");

                pstmt.setInt(1, corrida.getCodPista());
                pstmt.setInt(2, corrida.getPosicao().getPosicao());
                pstmt.setInt(3, corrida.getPontuacao().getPontos());
                pstmt.setInt(4, corrida.getEtapa());
                pstmt.setInt(5, corrida.getCodPiloto());

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
     * Metodo faz a criacao da conexao com o banco de dados, executa a exclusao dos
     * dados no banco e fecha a conexao.
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
                pstmt = conn.prepareStatement("delete from gridchegada where cod_prova = ? and cod_piloto = ?");

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
     * Metodo faz a criacao da conexao com o banco, faz um SELECT com os dados da
     * tabelaProva e retorna uma matriz com os dados referentes a essa tabela.
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
     * Metodo faz a criacao da conexao com o banco, faz um SELECT com os dados da
     * tabela Pista e retorna uma matriz com os dados referentes a essa tabela.
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
     * Metodo faz a criacao da conexao com o banco, faz um SELECT com os dados da
     * tabela Piloto e retorna uma matriz com os dados referentes a essa tabela.
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
     * Metodo que seleciona as provas por pilotos
     *
     * @param corrida
     * @return
     * @throws exception_RegistroJaExisteException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public String[][] selectProvaPiloto(vo.vo_Corrida corrida) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        int tam = 0;

        conn = this.getConnection();
        PreparedStatement pstmt;
        ResultSet rs;
        try{
            pstmt = conn.prepareStatement("select cod_piloto, pontuacao from gridchegada where cod_prova = ?");

            pstmt.setInt(1, corrida.getEtapa());
            
            ResultSet aux;
            aux = pstmt.executeQuery();

            while(aux.next()) {
                tam++;
            }
            String vetor[][] = new String[3][tam];

            int cont = 0;
            aux.close();

            pstmt = conn.prepareStatement("select cod_piloto, pontuacao from gridchegada where cod_prova = ?");

            pstmt.setInt(1, corrida.getEtapa());
            
            rs = pstmt.executeQuery();

            while(rs.next()){
                vetor[0][cont] = rs.getString("cod_piloto");
                vetor[2][cont] = rs.getString("pontuacao");
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

    /**
     * Seleciona o grid de chegada por corrida
     *
     * @param corrida
     * @return
     * @throws exception_RegistroJaExisteException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public String[][] selectChegada(vo_Corrida corrida) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException {
        int tam = 0;

        conn = this.getConnection();
        PreparedStatement pstmt;
        ResultSet rs;
        try{
            pstmt = conn.prepareStatement("select cod_piloto, posicao, pontuacao from gridchegada where cod_prova = ?");

            pstmt.setInt(1, corrida.getEtapa());

            ResultSet aux;
            aux = pstmt.executeQuery();

            while(aux.next()) {
                tam++;
            }
            String vetor[][] = new String[4][tam];

            int cont = 0;
            aux.close();

            pstmt = conn.prepareStatement("select cod_piloto, posicao, pontuacao from gridchegada where cod_prova = ?");

            pstmt.setInt(1, corrida.getEtapa());

            rs = pstmt.executeQuery();

            while(rs.next()){
                vetor[0][cont] = rs.getString("cod_piloto");
                vetor[2][cont] = rs.getString("posicao");
                vetor[3][cont] = rs.getString("pontuacao");
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

    /**
     * Seleciona os pontos de todos os pilotos
     *
     * @return
     * @throws exception_RegistroJaExisteException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public String[][] selectPontosPiloto() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
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
            String vetor[][] = new String[5][tam];

            int cont = 0;
            aux.close();

            pstmt = conn.prepareStatement("select cod_piloto, nome_piloto, cod_equipe from piloto");

            rs = pstmt.executeQuery();

            while(rs.next()){
                vetor[0][cont] = rs.getString("cod_piloto");
                vetor[1][cont] = rs.getString("nome_piloto");
                vetor[2][cont] = rs.getString("cod_equipe");
                cont++;
            }
            rs.close();
            conn.commit();
            pstmt.close();

            for (int i = 0; i < vetor[0].length; i++){
                pstmt = conn.prepareStatement("select nome_escuderia from escuderia where cod_escuderia = ?");
                pstmt.setInt(1, Integer.parseInt(vetor[2][i]));
                rs = pstmt.executeQuery();

                while(rs.next()){
                    vetor[3][i] = rs.getString("nome_escuderia");
                }
            }

            for (int i = 0; i < vetor[0].length; i++){
                pstmt = conn.prepareStatement("select pontuacao from gridchegada where cod_piloto = ?");
                pstmt.setInt(1, Integer.parseInt(vetor[0][i]));
                rs = pstmt.executeQuery();

                int pontos = 0;
                while(rs.next()){
                    pontos = pontos + rs.getInt("pontuacao");
                }
                vetor[4][i] = String.valueOf(pontos);
            }

            return vetor;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo cria a conexao com o banco, caso existir algum problema retorna uma excecao
     *
     * @return
     * @throws exception_RegistroJaExisteException
     */
    public Connection getConnection() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException {
        return dao.dao_GeraConnection.getConexao();
    }

    /**
     * Metodos seleciona os pontos de todas as equipes
     *
     * @return
     * @throws exception_RegistroJaExisteException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public String[][] selectPontosEquipe() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException {
        int tam = 0;
        conn = this.getConnection();

        PreparedStatement pstmt;
        ResultSet rs;
        ResultSet resSet;
        try{
            pstmt = conn.prepareStatement("select cod_escuderia, nome_escuderia from escuderia");

            ResultSet aux;
            aux = pstmt.executeQuery();

            while(aux.next()) {
                tam++;
            }
            String vetor[][] = new String[3][tam];

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

            for (int i = 0; i < vetor[0].length; i++){
                pstmt = conn.prepareStatement("select cod_piloto from piloto where cod_equipe = ?");
                pstmt.setInt(1, Integer.parseInt(vetor[0][i]));
                rs = pstmt.executeQuery();

                int pontos = 0;
                while(rs.next()){
                    pstmt = conn.prepareStatement("select pontuacao from gridchegada where cod_piloto = ?");
                    pstmt.setInt(1, rs.getInt("cod_piloto"));

                    resSet = pstmt.executeQuery();
                    while(resSet.next()){
                        pontos = pontos + resSet.getInt("pontuacao");
                    }
                    
                }
                vetor[2][i] = String.valueOf(pontos);
            }

            return vetor;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo seleciona os dados da evolucao dos pilotos
     *
     * @return
     * @throws exception_RegistroJaExisteException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public String[][] selectEvoPiloto() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        int tam = 0;
        conn = this.getConnection();

        PreparedStatement pstmt;
        ResultSet rs;
        try{
            pstmt = conn.prepareStatement("select cod_piloto, cod_Prova, pontuacao from gridchegada order by cod_piloto");

            ResultSet aux;
            aux = pstmt.executeQuery();

            while(aux.next()) {
                tam++;
            }
            String vetor[][] = new String[3][tam];

            int cont = 0;
            aux.close();

            pstmt = conn.prepareStatement("select cod_piloto, cod_Prova, pontuacao from gridchegada order by cod_piloto");

            rs = pstmt.executeQuery();

            while(rs.next()){
                vetor[0][cont] = rs.getString("cod_piloto");
                vetor[1][cont] = rs.getString("cod_prova");
                vetor[2][cont] = rs.getString("pontuacao");
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
     * Seleciona dados da evolucao da equipe
     * @return
     * @throws exception_RegistroJaExisteException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public String[][] selectEvoEquipe() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        int tam = 0;
        conn = this.getConnection();

        PreparedStatement pstmt;
        ResultSet rs;
        try{
            pstmt = conn.prepareStatement("select cod_piloto, cod_Prova, pontuacao from gridchegada order by cod_piloto");

            ResultSet aux;
            aux = pstmt.executeQuery();

            while(aux.next()) {
                tam++;
            }
            String vetor[][] = new String[4][tam];

            int cont = 0;
            aux.close();

            pstmt = conn.prepareStatement("select cod_piloto, cod_Prova, pontuacao from gridchegada order by cod_piloto");

            rs = pstmt.executeQuery();

            while(rs.next()){
                vetor[1][cont] = rs.getString("cod_piloto");
                vetor[2][cont] = rs.getString("cod_prova");
                vetor[3][cont] = rs.getString("pontuacao");
                cont++;
            }
            rs.close();
            conn.commit();
            pstmt.close();

            for (int i=0;i<vetor[1].length; i++){
                pstmt = conn.prepareStatement("select cod_equipe from piloto where cod_piloto = ?");
                pstmt.setInt(1, Integer.parseInt(vetor[1][i]));

                rs = pstmt.executeQuery();
                while(rs.next()){
                    vetor[0][i] = rs.getString("cod_equipe");
                }
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
    
}