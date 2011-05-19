package gui;
import dao.dao_Pista;
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
import vo.vo_Pista;

/**
 * Classe relativamente pronta conforme padroes atuais
 * Classe de interface grafica que se refere ao cadastro de pista, com os dados
 * referentes a esse tipo de dado e seus respectivos processos.
 * @author edipoterra
 */
public class gui_Pista extends javax.swing.JDialog implements PropertyChangeListener{
    //label
    private JLabel labelCodigo;
    private JLabel labelNome;
    private JLabel labelPais;

    //campos de texto
    private JFormattedTextField textCodigo;
    private JTextField textNome;
    private JComboBox comboPais;

    //botoes
    private JButton buttonInserir;
    private JButton buttonAlterar;
    private JButton buttonExcluir;
    private JButton buttonLimpar;
    private JButton buttonCancelar;

    //variaveis auxiliares
    private String matriz[][];
    private int aux;

    private Connection conn;
    /**
     * metodo construtor de pista, montando a tela de pistas
     */
    public gui_Pista() throws ClassNotFoundException, SQLException{
        this.criaComps();
        this.montaComps();

        this.setTitle("Cadastro Pista");
        this.setBounds(100, 100, 300, 240);
        this.setResizable(false);
        this.setModal(true);
        this.setVisible(true);

    }

    /**
     * metodo construtor com conexao
     * @param conn
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    gui_Pista(Connection conn) throws ClassNotFoundException, SQLException {
        this.criaComps();
        this.montaComps();

        this.setTitle("Cadastro Pista");
        this.setBounds(100, 100, 300, 240);
        this.setResizable(false);
        this.setModal(true);
        this.setVisible(true);

        this.conn = conn;
    }

    /**
     * metodo cria os componentes e inicializa o que for necessario em questao
     * de componentes graficos
     */
    public void criaComps() throws ClassNotFoundException, SQLException{
        //criando labels
        this.labelCodigo = new JLabel("Codigo");
        this.labelNome = new JLabel("Nome");
        this.labelPais = new JLabel("País");

        //criando campos de texto
        NumberFormat numero = NumberFormat.getInstance();
        this.textCodigo = new JFormattedTextField(numero);
        textCodigo.setColumns(10);
        textCodigo.addPropertyChangeListener("value", this);
        
        this.textNome = new JTextField();

        //criando combo
        dao.dao_Pista esc = new dao.dao_Pista(conn);
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
                        Logger.getLogger(gui_Pista.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(gui_Pista.class.getName()).log(Level.SEVERE, null, ex);
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
                        Logger.getLogger(gui_Pista.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(gui_Pista.class.getName()).log(Level.SEVERE, null, ex);
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
                        Logger.getLogger(gui_Pista.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(gui_Pista.class.getName()).log(Level.SEVERE, null, ex);
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
     * monta os componentes na tela de pistas, dando a dimensao e posicao de cada
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
     * Acao do botao cancelar
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
        vo_Pista pista = new vo_Pista();
        pista.setCodPista(codigo);
        pista.setNomePista(nome);
        pista.setCodPais(codPais);

        dao_Pista banco = new dao_Pista(conn);
        banco.insere(pista);
        
        JOptionPane.showMessageDialog(new JOptionPane(), "Pista inserida com sucesso","Aviso!",JOptionPane.INFORMATION_MESSAGE);
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
        
        vo_Pista pista = new vo_Pista();
        pista.setCodPista(codigo);
        pista.setNomePista(nome);
        pista.setCodPais(codPais);

        dao_Pista banco = new dao_Pista(conn);
        banco.insere(pista);
        JOptionPane.showMessageDialog(new JOptionPane(), "Pista alterada com sucesso","Aviso!",JOptionPane.INFORMATION_MESSAGE);
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
        
        vo_Pista pista = new vo_Pista();
        pista.setCodPista(codigo);
        pista.setNomePista(nome);
        pista.setCodPais(codPais);

        dao_Pista banco = new dao_Pista(conn);
        banco.exclui(pista);
        JOptionPane.showMessageDialog(new JOptionPane(), "Pista excluida com sucesso","Aviso!",JOptionPane.INFORMATION_MESSAGE);
        limpa();
    }

    /**
     * limpa todos os campos da tela, exceto os combos
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
     * metodo responsavel pelos campos de texto
     * @param evt
     */
    public void propertyChange(PropertyChangeEvent evt) {
        Object source = evt.getSource();
        if ((source == textCodigo)&&(textCodigo.getValue() != null)){
            String teste = "";
            teste = ((Number)textCodigo.getValue()).toString();
            try{
                aux = Integer.parseInt(teste);
                vo_Pista pista = new vo_Pista();
                pista.setCodPista(aux);
                dao_Pista banco = new dao_Pista(conn);
                try {
                    try {
                        pista = banco.selecionaPista(pista);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(gui_Pista.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(gui_Pista.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    textNome.setText(pista.getNomePista());
                    int index = 0;
                    boolean achou = false;
                    for (int i = 0; i < matriz[0].length; i++){
                        if (Integer.parseInt(matriz[0][i]) == pista.getCodPais()){
                            index = i;
                            achou = true;
                            break;
                        }
                    }
                    comboPais.setSelectedIndex(index);
                } catch (exception_RegistroJaExisteException ex) {
                    JOptionPane.showMessageDialog(new JOptionPane(), ex.getMessage(),"Ops...",JOptionPane.ERROR_MESSAGE);

                } 
            }
            catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(new JOptionPane(), "A quantidade de digitos exedeu o limite!","Ops...",JOptionPane.ERROR_MESSAGE);
                //seta focus para 1o campo
            }
        }
    }
}