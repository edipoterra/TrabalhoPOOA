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
import dao.dao_GridChegada;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author edipoterra
 */
public class gui_ResultChegada extends javax.swing.JDialog{
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
     * Metodo construtor de resultChegada
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public gui_ResultChegada() throws ClassNotFoundException, SQLException{
        this.criaComps();
        this.montaComps();

        this.setTitle("Consulta Grid de Chegada");
        this.setBounds(100, 100, 400, 310);
        this.setResizable(false);
        this.setModal(true);
        this.setVisible(true);

    }

    /**
     * Construtor resultChegada com conexao passada por parametro
     * @param conn
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public gui_ResultChegada(Connection conn) throws ClassNotFoundException, SQLException {
        this.criaComps();
        this.montaComps();

        this.setTitle("Consulta Grid de Chegada");
        this.setBounds(100, 100, 400, 310);
        this.setResizable(false);
        this.setModal(true);
        this.setVisible(true);

        this.conn = conn;
    }

    /**
     * Metodo que cria os componentes do resultado de chegada
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

        String[] colunas = new String []{"Codigo","Nome","Posicao", "Pontuacao"};
        String[][] dados = new String [][]{
        };
        tabela = new JTable();
        tabela.setModel(new DefaultTableModel(new Object [][] { }, new String [] {"Codigo", "Piloto", "Posicao", "Pontuacao"}));

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
     * metodo que faz a montagem de componentes dessa tela
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
     * Action performed do botao limpar
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
     * metodo que seleciona os dados e envia para o jtable
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

        dao_GridChegada chegada = new dao_GridChegada(conn);
        dados = chegada.selectChegada(corrida);
        for (int i = 0; i < dados[0].length; i++){
            dtm.addRow(new Object[]{dados[0][i], dados[1][i], dados[2][i], dados[3][i]});
        }
    }

    /**
     * Limpa jtable
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
     * Finaliza processos e fhecha tela
     */
    private void cancela() {
        this.hide();
    }

}
