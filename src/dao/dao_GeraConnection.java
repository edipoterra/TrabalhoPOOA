package dao;
import java.sql.*;

public class dao_GeraConnection{

    /**
     * Variavel estatica da conexao
     *
     */
    private static Connection instancia = null;

    /**
     * Metodo cria a conexao e passa ela por parametro, respeitando o Singleton
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    dao_GeraConnection() throws ClassNotFoundException, SQLException{

        Class.forName("org.hsqldb.jdbcDriver");


        instancia = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/formula1","sa","");
    }

    /**
     * Metodo que retorna a instancia da conexao
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConexao() throws ClassNotFoundException, SQLException{

        if(instancia==null){
            new dao_GeraConnection();
        }
        return instancia;
    }

    /**
     * Metodo faz o fechamento com a conexao
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void finalizaConexao() throws ClassNotFoundException, SQLException{
        if(instancia != null){
            instancia.close();
            instancia = null;
        }
    }

}
