package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import vo.vo_Corrida;

/**
 * classe responsavel em gravar os dados referentes ao cadastro de provas no banco de dados
 * 
 * @author edipoterra
 *
 */
public class dao_Provas {

    /**
     * Declaracao da variavel responsavel pela criacao de conexao com  banco
     */
    private Connection conn;

    /**
     * Metodo construtor que inicializa a conexao com o banco de dados
     *
     * @throws exception_RegistroJaExisteException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public dao_Provas() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        conn = getConnection();
    }

    /**
     * Metodo construtor que recebe a conexao por parametro
     *
     * @param conn
     */
    public dao_Provas(Connection conn){
        this.conn = conn;
    }

    /**
     * Metodo que faz a insercao de dados no banco, na tabela PROVA.
     * Possui como parametros um objeto do tipo vo.vo_Corrida, e referencias dando enfase
     * que pode existir erros do tipo exception_RegistroJaExisteException.
     * Por fim de todo o processo, fecha-se todas as conexoes
     *
     * @param prova
     * @return
     * @throws exception_RegistroJaExisteException
     */
    public int insere(vo.vo_Corrida prova) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        conn = this.getConnection();
        int inseriu;

        if (prova!=null){
            inseriu = prova.getEtapa();
            PreparedStatement pstmt;

            try{
                pstmt = conn.prepareStatement("insert into prova(cod_prova, nome_prova, cod_pista, cod_pais, num_voltas, data) values (?, ?, ?, ?, ?, ?)");

                pstmt.setInt(1, prova.getEtapa());
                pstmt.setString(2, prova.getNome());
                pstmt.setInt(3, prova.getCodPista());
                pstmt.setInt(4, prova.getCodPais());
                pstmt.setInt(5, prova.getNum_voltas());

                java.sql.Date data = new java.sql.Date(prova.getDiaCorrida().getYear(), prova.getDiaCorrida().getMonth(), prova.getDiaCorrida().getDate());
                pstmt.setDate(6, data);

                pstmt.execute();
                conn.commit();
                pstmt.close();

            } catch (SQLException e) {
                if(e.getErrorCode() == -104) {
                    throw new exception_RegistroJaExisteException("Provas");
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
     * Metodo que faz a alteracao de dados no banco, na tabela PROVA.
     * Possui como parametros um objeto do tipo vo.vo_Corrida, e referencias dando enfase
     * que pode existir erros do tipo exception_RegistroJaExisteException.
     * Por fim de todo o processo, fecha-se todas as conexoes
     *
     * @param prova
     * @return
     * @throws exception_RegistroJaExisteException
     */
    public int altera(vo.vo_Corrida prova) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        conn = this.getConnection();
        int alterou;

        if (prova!=null){
            alterou = prova.getEtapa();
            PreparedStatement pstmt;

            try{
                pstmt = conn.prepareStatement("update prova set nome_prova = ?, cod_pista = ?, cod_pais = ?, num_voltas = ?, data = ? where cod_prova = ?");

                pstmt.setString(1, prova.getNome());
                pstmt.setInt(2, prova.getCodPista());
                pstmt.setInt(3, prova.getCodPais());
                pstmt.setInt(4, prova.getNum_voltas());

                java.sql.Date data = new java.sql.Date(prova.getDiaCorrida().getYear(), prova.getDiaCorrida().getMonth(), prova.getDiaCorrida().getDate());
                pstmt.setDate(5, data);
                pstmt.setInt(6, prova.getEtapa());
                
                pstmt.execute();
                conn.commit();
                pstmt.close();

            } catch (SQLException e) {
                if(e.getErrorCode() == -104) {
                    throw new exception_RegistroJaExisteException("Provas");
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
     * Metodo que faz a exclusao dos dados no banco, na tabela PROVA.
     * Possui como parametros um objeto do tipo vo.vo_Corrida, e referencias dando enfase
     * que pode existir erros do tipo exception_RegistroJaExisteException.
     * Por fim de todo o processo, fecha-se todas as conexoes
     *
     * @param prova
     * @return
     * @throws exception_RegistroJaExisteException
     */
    public int exclui(vo.vo_Corrida prova) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        conn = this.getConnection();
        int alterou;

        if (prova!=null){
            alterou = prova.getEtapa();
            PreparedStatement pstmt;

            try{
                pstmt = conn.prepareStatement("delete from prova where cod_prova = ?");

                pstmt.setInt(1, prova.getCodPista());


                pstmt.execute();
                conn.commit();
                pstmt.close();

            } catch (SQLException e) {
                if(e.getErrorCode() == -104) {
                    throw new exception_RegistroJaExisteException("Provas");
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
     * Metodo responsavel por gerar a lista de pistas para popular os comboboxes.
     * Faz um select de codigo e nome das pistas e exporta uma matriz com esses dados.
     * Da mesma forma, pode gerar excecao, tanto por SQL quanto por problema de conexao com o banco.
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
     * Metodo responsavel por gerar a lista de paises para popular os comboboxes.
     * Faz um select de codigo e nome dos paises e exporta uma matriz com esses dados.
     * Da mesma forma, pode gerar excecao, tanto por SQL quanto por problema de conexao com o banco.
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
     * Metodo que faz a selecao de provas
     *
     * @param corrida
     * @return
     * @throws exception_RegistroJaExisteException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public vo_Corrida selecionaProva(vo_Corrida corrida) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        conn = this.getConnection();
        
        PreparedStatement pstmt;
        ResultSet rs;
        try{
            pstmt = conn.prepareStatement("select cod_prova, nome_prova, cod_pista, cod_pais, num_voltas, data from prova where cod_prova = ?");
            pstmt.setInt(1, corrida.getEtapa());

            rs = pstmt.executeQuery();

            while(rs.next()){
                corrida.setEtapa(Integer.parseInt(rs.getString("cod_prova")));
                corrida.setNome(rs.getString("nome_prova"));
                corrida.setCodPista(rs.getInt("cod_pista"));
                corrida.setCodPais(rs.getInt("cod_pais"));
                corrida.setNum_voltas(rs.getInt("num_voltas"));

                String dataAux;
                dataAux = rs.getDate("data").toString();
                Date dataAux2 = new Date();
                dataAux2 = java.sql.Date.valueOf(dataAux);
                corrida.setDiaCorrida(dataAux2);
            }
            rs.close();
            conn.commit();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return corrida;
    }

    /**
     * metodo que faz consulta de banco para gerar a relacao global de provas
     *
     * @return
     * @throws exception_RegistroJaExisteException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public String[][] selectRelacaoProvas() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        int tam = 0;
        conn = this.getConnection();
        PreparedStatement pstmt;
        ResultSet rs;
        ResultSet resSet;
        try{
            pstmt = conn.prepareStatement("select cod_prova, nome_prova, cod_pista, cod_pais from prova");

            ResultSet aux;
            aux = pstmt.executeQuery();

            while(aux.next()) {
                tam++;
            }

            String vetor[][] = new String[7][tam];

            int cont = 0;
            aux.close();

            pstmt = conn.prepareStatement("select cod_prova, nome_prova, cod_pista, cod_pais, num_voltas from prova");

            rs = pstmt.executeQuery();

            while(rs.next()){
                vetor[0][cont] = rs.getString("cod_prova");
                vetor[1][cont] = rs.getString("nome_prova");
                vetor[2][cont] = rs.getString("cod_pista");
                vetor[4][cont] = rs.getString("cod_pais");
                vetor[6][cont] = rs.getString("num_voltas");
                cont++;
            }
            rs.close();
            conn.commit();
            pstmt.close();

            for (int i = 0; i < vetor[0].length; i++){
                pstmt = conn.prepareStatement("select nome_pista from pista where cod_pista = ?");
                pstmt.setInt(1, Integer.parseInt(vetor[2][i]));
                rs = pstmt.executeQuery();
                while(rs.next()){
                    vetor[3][i] = rs.getString("nome_pista");
                }
                rs.close();
                conn.commit();
                pstmt.close();

                pstmt = conn.prepareStatement("select nome_pais from pais where cod_pais = ?");
                pstmt.setInt(1, Integer.parseInt(vetor[4][i]));
                rs = pstmt.executeQuery();

                while(rs.next()){
                    vetor[5][i] = rs.getString("nome_pais");
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
     * Metodo gera conexao, para se conectar com o banco.
     *
     * @return
     * @throws exception_RegistroJaExisteException
     */
    public Connection getConnection() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException {
        return dao_GeraConnection.getConexao();
    }   
}