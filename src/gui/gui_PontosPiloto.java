package gui;

import dao.exception_RegistroJaExisteException;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import dao.dao_GridChegada;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author edipoterra
 */
public class gui_PontosPiloto extends javax.swing.JDialog{
    private JLabel labelCodigo;
    private JComboBox comboProva;
    private JTable tabela;
    private JScrollPane scroll;

    private JButton selecionar;
    private JButton limpar;
    private JButton cancelar;

    private String[][] matriz1;
    private String[][] matriz2;
    private DefaultTableModel dtm;

    private Connection conn;

    /**
     * Contrutor da classe PontosPiloto
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws exception_RegistroJaExisteException
     */
    public gui_PontosPiloto() throws ClassNotFoundException, SQLException, exception_RegistroJaExisteException{
        this.criaComps();
        this.montaComps();

        this.setTitle("Consulta Pontos do Piloto");
        this.setBounds(100, 100, 400, 310);
        this.setResizable(false);
        this.setModal(true);
        this.setVisible(true);

    }

    /**
     * Contrutor da classe PontosPiloto com conexao passada por parametro
     * @param conn
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws exception_RegistroJaExisteException
     */
    public gui_PontosPiloto(Connection conn) throws ClassNotFoundException, SQLException, exception_RegistroJaExisteException {
        this.criaComps();
        this.montaComps();

        this.setTitle("Consulta Pontos do Piloto");
        this.setBounds(100, 100, 400, 310);
        this.setResizable(false);
        this.setModal(true);
        this.setVisible(true);

        this.conn = conn;
    }

    /**
     * Cria componentes graficos da classe
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void criaComps() throws ClassNotFoundException, SQLException {

        String[] colunas = new String []{"Codigo","Nome","Codigo Equipe", "Equipe", "Pontos"};
        String[][] dados = new String [][]{
        };
        tabela = new JTable();
        tabela.setModel(new DefaultTableModel(new Object [][] { }, new String [] {"Codigo", "Nome", "Codigo Equipe", "Equipe", "Pontos"}));

        dtm = (DefaultTableModel)tabela.getModel();

        this.scroll = new JScrollPane(this.tabela);

        this.cancelar = new JButton("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        getContentPane().setLayout(null);
    }

    /**
     * Metodo responsavel por montar os componentes graficos na tela
     * @throws exception_RegistroJaExisteException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void montaComps() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException {
        this.scroll.setBounds(10, 10, 380, 230);
        this.add(this.scroll);
        this.cancelar.setBounds(300, 250, 90, 30);
        this.add(this.cancelar);
        seleciona();
    }

    /**
     * Action performed do botao selecionar
     * @param evt
     * @throws exception_RegistroJaExisteException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void selecionarActionPerformed(ActionEvent evt) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        seleciona();
    }

    /**
     * Action Performed do botao limpar
     * @param evt
     */
    public void limparActionPerformed(ActionEvent evt){
        limpa();
    }

    /**
     * Action performed do botao cancelar
     * @param evt
     */
    public void cancelarActionPerformed(ActionEvent evt){
        cancela();
    }

    /**
     * metodo que seleciona os pontos por pilotos
     * @throws exception_RegistroJaExisteException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void seleciona() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException {
        int cont = dtm.getRowCount();
        cont--;

        for(int i = cont; i>=0; i--){
            dtm.removeRow(i);
        }

        int prova = 0;

        String[][] dados;

        dao_GridChegada chegada = new dao_GridChegada(conn);
        dados = chegada.selectPontosPiloto();
        for (int i = 0; i < dados[0].length; i++){
            dtm.addRow(new Object[]{dados[0][i], dados[1][i], dados[2][i], dados[3][i], dados[4][i]});
        }
    }

    /**
     * limpa jtable
     */
    private void limpa() {
        //limpa tabela
        int cont = dtm.getRowCount();
        cont--;

        for(int i = cont; i>=0; i--){
            dtm.removeRow(i);
        }
    }

    /**
     * Cancela os processos e fecha a tela
     */
    private void cancela() {
        this.hide();
    }

}
