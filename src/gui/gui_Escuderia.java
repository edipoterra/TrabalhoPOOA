package gui;
import dao.dao_Escuderia;
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
import vo.vo_Escuderia;

/**
 * Classe pronta, nos padroes atuais
 * 
 * @author edipoterra
 */
public class gui_Escuderia extends javax.swing.JDialog implements PropertyChangeListener{
    //cria label
    private JLabel labelCodigo;
    private JLabel labelNome;
    private JLabel labelPais;

    //cria campos de texto
    private JFormattedTextField textCodigo;
    private JTextField textNome;
    private JComboBox comboPais;

    //cria botoes
    private JButton buttonInserir;
    private JButton buttonAlterar;
    private JButton buttonExcluir;
    private JButton buttonLimpar;
    private JButton buttonCancelar;

    private int aux;
    private String matriz[][];

    private Connection conn;

    /**
     * construtor da tela de escuderia 
     */
    public gui_Escuderia() throws ClassNotFoundException, SQLException{
        this.criaComps();
        this.montaComps();

        this.setTitle("Cadastro Escuderia");
        this.setBounds(100, 100, 300, 240);
        this.setResizable(false);
        this.setModal(true);
        this.setVisible(true);
    }

    /**
     * Metodo construtor para Escuderia com conexao 
     * @param conn
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    gui_Escuderia(Connection conn) throws ClassNotFoundException, SQLException {
        this.criaComps();
        this.montaComps();

        this.setTitle("Cadastro Escuderia");
        this.setBounds(100, 100, 300, 240);
        this.setResizable(false);
        this.setModal(true);
        this.setVisible(true);

        this.conn = conn;
    }

    /**
     * cria componentes da tela
     */
    @SuppressWarnings("empty-statement")
    public void criaComps() throws ClassNotFoundException, SQLException{
        //criando labels
        this.labelCodigo = new JLabel("Codigo");
        this.labelNome = new JLabel("Nome");
        this.labelPais = new JLabel("País");

        //criando campos de texto
        NumberFormat numero;
        numero = NumberFormat.getNumberInstance();
        this.textCodigo = new JFormattedTextField(numero);
        textCodigo.setColumns(10);
        textCodigo.addPropertyChangeListener("value", this);


        this.textNome = new JTextField();

        //criando combo
        dao.dao_Escuderia esc = new dao.dao_Escuderia(conn);
        try {
            matriz = esc.select();
        } catch (exception_RegistroJaExisteException ex) {
            JOptionPane.showMessageDialog(new JOptionPane(), ex.getMessage(),"Ops...",JOptionPane.ERROR_MESSAGE);
        }
        this.comboPais = new JComboBox(matriz[1]);

        //criando botoes
        this.buttonInserir = new JButton("Inserir");
        buttonInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    try {
                        buttonInserirActionPerformed(evt);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(gui_Escuderia.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(gui_Escuderia.class.getName()).log(Level.SEVERE, null, ex);
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
                        buttonAlterarActionPerformed(evt);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(gui_Escuderia.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(gui_Escuderia.class.getName()).log(Level.SEVERE, null, ex);
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
                        Logger.getLogger(gui_Escuderia.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(gui_Escuderia.class.getName()).log(Level.SEVERE, null, ex);
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
     * monta os componentes em seus devidos lugares
     */
    public void montaComps(){
        //monta labels
        this.labelCodigo.setBounds(10, 20, 60, 15);
        this.labelNome.setBounds(10, 60, 60, 15);
        this.labelPais.setBounds(10, 100, 60, 15);
        this.add(this.labelCodigo);
        this.add(this.labelNome);
        this.add(this.labelPais);

        //monta campos texto
        this.textCodigo.setBounds(60, 10, 150, 30);
        this.textNome.setBounds(60, 50, 150, 30);
        this.comboPais.setBounds(60, 90,150, 30);
        this.add(this.textCodigo);
        this.add(this.textNome);
        this.add(this.comboPais);

        //monta botoes
        this.buttonInserir.setBounds(10, 130, 90, 30);
        this.buttonAlterar.setBounds(100, 130, 90, 30);
        this.buttonExcluir.setBounds(190, 130, 90, 30);
        this.buttonLimpar.setBounds(10, 160, 90, 30);
        this.buttonCancelar.setBounds(100, 160, 90, 30);
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
        int codigo = 0;
        String nome = "";
        int codPais = 0;
        codigo = ((Number)textCodigo.getValue()).intValue();
        nome = textNome.getText();
        codPais = Integer.parseInt(matriz[0][comboPais.getSelectedIndex()]);
        vo_Escuderia equipe = new vo_Escuderia();
        equipe.setCodigo(codigo);
        equipe.setNome(nome);
        equipe.setPais(codPais);

        dao_Escuderia banco = new dao_Escuderia(conn);
        banco.insere(equipe);
        JOptionPane.showMessageDialog(new JOptionPane(), "Escuderia inserida com sucesso","Aviso!",JOptionPane.INFORMATION_MESSAGE);
        limpa();
    }

    /**
     * metodo pega os dados dos campos da tela e manda para a camada de negocio, no fim
     * caso nao ocorrer excecao, retorna uma mensagem de processo OK
     *
     * @throws exception_RegistroJaExisteException
     */
    public void altera() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        int codigo = 0;
        String nome = "";
        int codPais = 0;
        codigo = ((Number)textCodigo.getValue()).intValue();
        nome = textNome.getText();
        codPais = Integer.parseInt(matriz[0][comboPais.getSelectedIndex()]);
        vo_Escuderia equipe = new vo_Escuderia();
        equipe.setCodigo(codigo);
        equipe.setNome(nome);
        equipe.setPais(codPais);

        dao_Escuderia banco = new dao_Escuderia(conn);
        banco.altera(equipe);
        JOptionPane.showMessageDialog(new JOptionPane(), "Escuderia alterada com sucesso","Aviso!",JOptionPane.INFORMATION_MESSAGE);
        limpa();
    }

    /**
     * metodo pega os dados dos campos da tela e manda para a camada de negocio, no fim
     * caso nao ocorrer excecao, retorna uma mensagem de processo OK
     *
     * @throws exception_RegistroJaExisteException
     */
    public void exclui() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        int codigo = 0;
        String nome = "";
        int codPais = 0;
        codigo = ((Number)textCodigo.getValue()).intValue();
        nome = textNome.getText();
        codPais = Integer.parseInt(matriz[0][comboPais.getSelectedIndex()]);
        vo_Escuderia equipe = new vo_Escuderia();
        equipe.setCodigo(codigo);
        equipe.setNome(nome);
        equipe.setPais(codPais);

        dao_Escuderia banco = new dao_Escuderia(conn);
        banco.exclui(equipe);
        JOptionPane.showMessageDialog(new JOptionPane(), "Escuderia excluida com sucesso","Aviso!",JOptionPane.INFORMATION_MESSAGE);
        limpa();
    }

    /**
     * limpa todos os campos da tela, exceto combos
     */
    public void limpa(){
        textCodigo.setText("");
        textNome.setText("");
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
        if ((source == textCodigo)&&(textCodigo.getValue() != null)){
            String teste = "";
            teste = ((Number)textCodigo.getValue()).toString();
            try{
                aux = Integer.parseInt(teste);
                vo_Escuderia equipe = new vo_Escuderia();
                equipe.setCodigo(aux);
                try{
                    dao_Escuderia banco = new dao_Escuderia(conn);
                    try {
                        equipe = banco.selecionaEquipe(equipe);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(gui_Escuderia.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(gui_Escuderia.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    textNome.setText(equipe.getNome());
                    int index = 0;
                    boolean achou = false;
                    for (int i = 0; i < matriz[0].length; i++){
                        if (Integer.parseInt(matriz[0][i]) == equipe.getPais()){
                            index = i;
                            achou = true;
                            break;
                        }
                    }
                    comboPais.setSelectedIndex(index);
                }
                catch (exception_RegistroJaExisteException ex){
                    JOptionPane.showMessageDialog(new JOptionPane(), ex.getMessage(),"Ops...",JOptionPane.ERROR_MESSAGE);
                }
            }
            catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(new JOptionPane(), "A quantidade de digitos exedeu o limite!","Ops...",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}