package gui;

import dao.exception_RegistroJaExisteException;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import vo.vo_Corrida;
import dao.dao_GridLargada;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author edipoterra
 */
public class gui_ResultLargada extends javax.swing.JDialog{
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
     * metodo construtor de ResultLargada
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public gui_ResultLargada() throws ClassNotFoundException, SQLException{
        this.criaComps();
        this.montaComps();

        this.setTitle("Consulta Grid de Largada");
        this.setBounds(100, 100, 400, 310);
        this.setResizable(false);
        this.setModal(true);
        this.setVisible(true);

    }

    /**
     * metodo construtor de resultLargada com Conexao por parametro
     * @param conn
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public gui_ResultLargada(Connection conn) throws ClassNotFoundException, SQLException {
        this.criaComps();
        this.montaComps();

        this.setTitle("Consulta Grid de Largada");
        this.setBounds(100, 100, 400, 310);
        this.setResizable(false);
        this.setModal(true);
        this.setVisible(true);

        this.conn = conn;
    }

    /**
     * Cria componentes graficos de ResultLargada
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void criaComps() throws ClassNotFoundException, SQLException {
        this.labelCodigo = new JLabel("Prova");

        //criando combo
        dao.dao_GridChegada esc = new dao.dao_GridChegada(conn);
        try {
            matriz1 = esc.selectProva();
        } catch (exception_RegistroJaExisteException ex) {
            JOptionPane.showMessageDialog(new JOptionPane(), ex.getMessage(),"Ops...",JOptionPane.ERROR_MESSAGE);
        }
        this.comboProva = new JComboBox(matriz1[1]);

        String[] colunas = new String []{"Codigo","Nome","Posicao"};
        String[][] dados = new String [][]{
        };
        tabela = new JTable();
        tabela.setModel(new DefaultTableModel(new Object [][] { }, new String [] {"Codigo", "Piloto", "Posicao"}));

        dtm = (DefaultTableModel)tabela.getModel();

        this.scroll = new JScrollPane(this.tabela);


        this.selecionar = new JButton("Inserir");
        selecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    try {
                        selecionarActionPerformed(evt);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(gui_PilotoProva.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(gui_PilotoProva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (exception_RegistroJaExisteException ex) {
                    Logger.getLogger(gui_PilotoProva.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.limpar = new JButton("Limpar");
        limpar.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                limparActionPerformed(evt);
            }
        });
        this.cancelar = new JButton("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        getContentPane().setLayout(null);
    }

    /**
     * Monta componentes da tela ResultLargada
     */
    private void montaComps() {
        //monta labels
        this.labelCodigo.setBounds(10, 20, 100, 15);
        this.add(this.labelCodigo);

        //monta campos texto
        this.comboProva.setBounds(120, 10, 150, 30);
        this.add(this.comboProva);

        //monta jtable
        this.scroll.setBounds(10, 40, 380, 200);
        this.add(this.scroll);

        //monta botoes
        this.selecionar.setBounds(10, 250, 90, 30);
        this.limpar.setBounds(100, 250, 90, 30);
        this.cancelar.setBounds(190, 250, 90, 30);
        this.add(this.selecionar);
        this.add(this.limpar);
        this.add(this.cancelar);
    }

    /**
     * Action Performed de botao selecionar
     * @param evt
     * @throws exception_RegistroJaExisteException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void selecionarActionPerformed(ActionEvent evt) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        seleciona();
    }

    /**
     * Action performed de botao limpar
     * @param evt
     */
    public void limparActionPerformed(ActionEvent evt){
        limpa();
    }

    /**
     * action performed de botao cancelar
     * @param evt
     */
    public void cancelarActionPerformed(ActionEvent evt){
        cancela();
    }

    /**
     * metodo referente ao botao selecionar
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
        prova = Integer.parseInt(matriz1[0][comboProva.getSelectedIndex()]);

        vo_Corrida corrida = new vo_Corrida();
        corrida.setEtapa(prova);

        String[][] dados;

        dao_GridLargada largada = new dao_GridLargada(conn);
        dados = largada.selectLargada(corrida);
        for (int i = 0; i < dados[0].length; i++){
            dtm.addRow(new Object[]{dados[0][i], dados[1][i], dados[2][i]});
        }

        //seleciona as demais camadas e manda para o JTable
    }

    /**
     * limpa JTable
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
     * Cancela o processo e fecha a tela corrente
     */
    private void cancela() {
        this.hide();
    }

}
