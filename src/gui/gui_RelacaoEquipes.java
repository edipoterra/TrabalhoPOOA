package gui;

import dao.dao_Escuderia;
import dao.exception_RegistroJaExisteException;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author edipoterra
 */
public class gui_RelacaoEquipes extends javax.swing.JDialog{
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
     * metodo construtor de relacao de equipes
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws exception_RegistroJaExisteException
     */
    public gui_RelacaoEquipes() throws ClassNotFoundException, SQLException, exception_RegistroJaExisteException{
        this.criaComps();
        this.montaComps();

        this.setTitle("Consulta Pontos do Piloto");
        this.setBounds(100, 100, 400, 310);
        this.setResizable(false);
        this.setModal(true);
        this.setVisible(true);

    }

    /**
     * metodo construtor com conexao passada por parametro
     * @param conn
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws exception_RegistroJaExisteException
     */
    public gui_RelacaoEquipes(Connection conn) throws ClassNotFoundException, SQLException, exception_RegistroJaExisteException {
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
     * cria componentes da tela
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void criaComps() throws ClassNotFoundException, SQLException {
        String[] colunas = new String []{"Codigo","Nome","Pais"};
        String[][] dados = new String [][]{};
        tabela = new JTable();
        tabela.setModel(new DefaultTableModel(new Object [][] { }, new String [] {"Codigo","Nome","Pais"}));

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
     * monta os componentes graficos na tela
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
     * Action Performed do botao selecionar
     * @param evt
     * @throws exception_RegistroJaExisteException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void selecionarActionPerformed(ActionEvent evt) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        seleciona();
    }

    /**
     * Action performed do botao limpar
     * @param evt
     */
    public void limparActionPerformed(ActionEvent evt){
        limpa();
    }

    /**
     * action performed do botao cancelar
     * @param evt
     */
    public void cancelarActionPerformed(ActionEvent evt){
        cancela();
    }

    /**
     * Seleciona os dados para a relacao de equipes
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

        dao_Escuderia equipe = new dao_Escuderia(conn);
        dados = equipe.selectRelacaoEquipe();
        for (int i = 0; i < dados[0].length; i++){
            dtm.addRow(new Object[]{dados[0][i], dados[1][i], dados[3][i]});
        }

    }

    /**
     * limpa jtable
     */
    private void limpa() {
        int cont = dtm.getRowCount();
        cont--;

        for(int i = cont; i>=0; i--){
            dtm.removeRow(i);
        }
    }

    /**
     * Cancela os processos e fecha tela corrente
     */
    private void cancela() {
        this.hide();
    }

}
