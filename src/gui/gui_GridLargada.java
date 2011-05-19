package gui;
import dao.dao_GridLargada;
import dao.exception_RegistroJaExisteException;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import vo.vo_Corrida;
import vo.vo_Posicao;

/**
 *
 * @author edipoterra
 */
public class gui_GridLargada extends JDialog implements PropertyChangeListener{
    private JLabel labelProva;
    private JLabel labelPista;
    private JLabel labelNomePiloto;
    private JLabel labelPosicao;

    private JComboBox comboProva;
    private JComboBox comboPista;
    private JComboBox comboPiloto;
    private JFormattedTextField textPosicao;
    private JButton buttonInserir;
    private JButton buttonAlterar;
    private JButton buttonExcluir;
    private JButton buttonLimpar;
    private JButton buttonCancelar;

    private String[][] matriz1;
    private String[][] matriz2;
    private String[][] matriz3;

    private Connection conn;

    /**
     * contrutor de grid de largada 
     */
    public gui_GridLargada() throws ClassNotFoundException, SQLException{
        this.criaComps();
        this.montaComps();

        this.setTitle("Registro Grid de Largada");
        this.setBounds(100, 100, 300, 260);
        this.setResizable(false);
        this.setModal(true);
        this.setVisible(true);

    }

    /**
     * construtor de grid de largada com conexao
     * @param conn
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    gui_GridLargada(Connection conn) throws ClassNotFoundException, SQLException {
        this.criaComps();
        this.montaComps();

        this.setTitle("Registro Grid de Largada");
        this.setBounds(100, 100, 300, 260);
        this.setResizable(false);
        this.setModal(true);
        this.setVisible(true);

        this.conn = conn;
    }

    /**
     * cria componentes da tela grafica
     */
    public void criaComps() throws ClassNotFoundException, SQLException{
        //criando labels
        this.labelProva = new JLabel("Prova");
        this.labelPista = new JLabel("Pista");
        this.labelNomePiloto = new JLabel("Piloto");
        this.labelPosicao = new JLabel("Posicao");

        //criando campos de texto
        NumberFormat numero;
        numero = NumberFormat.getNumberInstance();
        this.textPosicao = new JFormattedTextField(numero);
        textPosicao.setColumns(10);
        textPosicao.addPropertyChangeListener("value", this);

        dao.dao_GridLargada grid = new dao.dao_GridLargada(conn);
        try {
            matriz1 = grid.selectProva();
        } catch (exception_RegistroJaExisteException ex) {
            JOptionPane.showMessageDialog(new JOptionPane(), ex.getMessage(),"Ops...",JOptionPane.ERROR_MESSAGE);
        }
        this.comboProva = new JComboBox(matriz1[1]);
        try {
            matriz2 = grid.selectPista();
        } catch (exception_RegistroJaExisteException ex) {
            JOptionPane.showMessageDialog(new JOptionPane(), ex.getMessage(),"Ops...",JOptionPane.ERROR_MESSAGE);
        }
        this.comboPista = new JComboBox(matriz2[1]);
        try {
            matriz3 = grid.selectPiloto();
        } catch (exception_RegistroJaExisteException ex) {
            JOptionPane.showMessageDialog(new JOptionPane(), ex.getMessage(),"Ops...",JOptionPane.ERROR_MESSAGE);
        }
        this.comboPiloto = new JComboBox(matriz3[1]);

        //criando botoes
        this.buttonInserir = new JButton("Inserir");
        buttonInserir.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                try {
                    try {
                        buttonInserirActionPerformed(evt);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(gui_GridLargada.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(gui_GridLargada.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (exception_RegistroJaExisteException ex) {
                    JOptionPane.showMessageDialog(new JOptionPane(), ex.getMessage(),"Ops...",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        this.buttonAlterar = new JButton("Alterar");
        buttonAlterar.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                try {
                    try {
                        try {
                            buttonAlterarActionPerformed(evt);
                        } catch (SQLException ex) {
                            Logger.getLogger(gui_GridLargada.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(gui_GridLargada.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (exception_RegistroJaExisteException ex) {
                    JOptionPane.showMessageDialog(new JOptionPane(), ex.getMessage(),"Ops...",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        this.buttonExcluir = new JButton("Excluir");
        buttonExcluir.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                try {
                    try {
                        buttonExcluirActionPerformed(evt);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(gui_GridLargada.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(gui_GridLargada.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (exception_RegistroJaExisteException ex) {
                    JOptionPane.showMessageDialog(new JOptionPane(), ex.getMessage(),"Ops...",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        this.buttonLimpar = new JButton("Limpar");
        buttonLimpar.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                buttonLimparActionPerformed(evt);
            }
        });
        this.buttonCancelar = new JButton("Cancelar");
        buttonCancelar.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                buttonCancelarActionPerformed(evt);
            }
        });

        getContentPane().setLayout(null);
    }

    /**
     * monta componentes em seus devidos lugares
     */
    public void montaComps(){
        //monta labels
        this.labelProva.setBounds(10, 20, 100, 15);
        this.labelPista.setBounds(10, 60, 100, 15);
        this.labelNomePiloto.setBounds(10, 100, 100, 15);
        this.labelPosicao.setBounds(10, 140, 100, 15);
        this.add(this.labelProva);
        this.add(this.labelPista);
        this.add(this.labelNomePiloto);
        this.add(this.labelPosicao);

        //monta campos texto
        this.comboProva.setBounds(120, 10, 150, 30);
        this.comboPista.setBounds(120, 50, 150, 30);
        this.comboPiloto.setBounds(120, 90, 150, 30);
        this.textPosicao.setBounds(120, 130, 150, 30);
        this.add(this.comboProva);
        this.add(this.comboPista);
        this.add(this.comboPiloto);
        this.add(this.textPosicao);

        //monta botoes
        this.buttonInserir.setBounds(10, 170, 90, 30);
        this.buttonAlterar.setBounds(100, 170, 90, 30);
        this.buttonExcluir.setBounds(190, 170, 90, 30);
        this.buttonLimpar.setBounds(10, 200, 90, 30);
        this.buttonCancelar.setBounds(100, 200, 90, 30);
        this.add(this.buttonInserir);
        this.add(this.buttonAlterar);
        this.add(this.buttonExcluir);
        this.add(this.buttonLimpar);
        this.add(this.buttonCancelar);
    }

    /**
     * acao do botao inserir
     *
     * @param evt
     * @throws exception_RegistroJaExisteException
     */
    public void buttonInserirActionPerformed(java.awt.event.ActionEvent evt) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        insere();
    }

    /**
     * acao do botao alterar
     *
     * @param evt
     * @throws exception_RegistroJaExisteException
     */
    public void buttonAlterarActionPerformed(java.awt.event.ActionEvent evt) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        altera();
    }

    /**
     * acao do botao excluir
     *
     * @param evt
     * @throws exception_RegistroJaExisteException
     */
    public void buttonExcluirActionPerformed(java.awt.event.ActionEvent evt) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        exclui();
    }

    /**
     * acao do botao limpar
     *
     * @param evt
     */
    public void buttonLimparActionPerformed(java.awt.event.ActionEvent evt){
        limpa();
    }

    /**
     * acao do botao cancelar
     *
     * @param evt
     */
    public void buttonCancelarActionPerformed(java.awt.event.ActionEvent evt){
        cancela();
    }

    /**
     * metodo pega os dados dos campos da tela e manda para a camada de negocio, no fim
     * caso nao ocorrer excecao, retorna uma mensagem de processo OK
     *
     * @throws exception_RegistroJaExisteException
     */
    public void insere() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        int prova = 0;
        int pista = 0;
        int piloto = 0;
        int posicao = 0;

        prova = Integer.parseInt(matriz1[0][comboProva.getSelectedIndex()]);

        pista = Integer.parseInt(matriz2[0][comboPista.getSelectedIndex()]);

        piloto = Integer.parseInt(matriz3[0][comboPiloto.getSelectedIndex()]);

        posicao = ((Number)textPosicao.getValue()).intValue();
        vo_Posicao pos = new vo_Posicao();


        vo_Corrida corrida = new vo_Corrida();
        corrida.setEtapa(prova);
        corrida.setCodPista(pista);
        corrida.setCodPiloto(piloto);
        pos.setPosicao(posicao);
        corrida.setPosicao(pos);

        dao_GridLargada largada = new dao_GridLargada(conn);
        largada.insere(corrida);
        JOptionPane.showMessageDialog(new JOptionPane(), "Grid de largada inserido com sucesso","Aviso!",JOptionPane.INFORMATION_MESSAGE);
        limpa();
    }

    /**
     * metodo pega os dados dos campos da tela e manda para a camada de negocio, no fim
     * caso nao ocorrer excecao, retorna uma mensagem de processo OK
     *
     * @throws exception_RegistroJaExisteException
     */
    public void altera() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        int prova = 0;
        int pista = 0;
        int piloto = 0;
        int posicao = 0;

        prova = Integer.parseInt(matriz1[0][comboProva.getSelectedIndex()]);

        pista = Integer.parseInt(matriz2[0][comboPista.getSelectedIndex()]);

        piloto = Integer.parseInt(matriz3[0][comboPiloto.getSelectedIndex()]);

        posicao = ((Number)textPosicao.getValue()).intValue();
        vo_Posicao pos = new vo_Posicao();

        vo_Corrida corrida = new vo_Corrida();
        corrida.setEtapa(prova);
        corrida.setCodPista(pista);
        corrida.setCodPiloto(piloto);
        pos.setPosicao(posicao);
        corrida.setPosicao(pos);

        dao_GridLargada largada = new dao_GridLargada(conn);
        largada.altera(corrida);
        JOptionPane.showMessageDialog(new JOptionPane(), "Grid de largada alterado com sucesso","Aviso!",JOptionPane.INFORMATION_MESSAGE);
        limpa();    }

    /**
     * metodo pega os dados dos campos da tela e manda para a camada de negocio, no fim
     * caso nao ocorrer excecao, retorna uma mensagem de processo OK
     *
     * @throws exception_RegistroJaExisteException
     */
    public void exclui() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        int prova = 0;
        int pista = 0;
        int piloto = 0;
        int posicao = 0;

        prova = Integer.parseInt(matriz1[0][comboProva.getSelectedIndex()]);

        pista = Integer.parseInt(matriz2[0][comboPista.getSelectedIndex()]);

        piloto = Integer.parseInt(matriz3[0][comboPiloto.getSelectedIndex()]);

        posicao = ((Number)textPosicao.getValue()).intValue();
        vo_Posicao pos = new vo_Posicao();


        vo_Corrida corrida = new vo_Corrida();
        corrida.setEtapa(prova);
        corrida.setCodPista(pista);
        corrida.setCodPiloto(piloto);
        pos.setPosicao(posicao);
        corrida.setPosicao(pos);

        dao_GridLargada largada = new dao_GridLargada(conn);
        largada.exclui(corrida);
        JOptionPane.showMessageDialog(new JOptionPane(), "Grid de largada excluido com sucesso","Aviso!",JOptionPane.INFORMATION_MESSAGE);
        limpa();    }

    /**
     * limpa todos os campos da tela, exceto combos
     */
    public void limpa(){
        textPosicao.setText("");
    }

    /**
     * fecha a tela sem salvar nada
     */
    public void cancela(){
        this.hide();
    }

    /**
     * metodo que trata os campos de texto
     * @param evt
     */
    public void propertyChange(PropertyChangeEvent evt) {
        Object source = evt.getSource();
        int aux = 0;
        if ((source == textPosicao)&&(textPosicao.getValue() != null)){
            String teste = "";
            teste = ((Number)textPosicao.getValue()).toString();
            try{
                aux = Integer.parseInt(teste);
            }
            catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(new JOptionPane(), "A quantidade de digitos exedeu o limite!","Ops...",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}