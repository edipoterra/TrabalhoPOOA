package gui;
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
import javax.swing.JOptionPane;
import vo.vo_Pais;
import dao.dao_Pais;

/**
 * Classe pronta nos padroes atuais
 * 
 * @author edipoterra
 */
public class gui_Pais extends javax.swing.JDialog implements PropertyChangeListener{
    private JLabel labelCodigo;
    private JLabel labelNome;

    private JFormattedTextField textCodigo;
    private JTextField textNome;

    private JButton buttonInserir;
    private JButton buttonAlterar;
    private JButton buttonExcluir;
    private JButton buttonLimpar;
    private JButton buttonCancelar;

    private int aux;
    private int indice;

    private Connection conn;

    /*
     * As chamadas da camada VO e da camada DAO serao instanciadas e usadas nessa classe
     * onde vai passar por parametros apenas o objeto vo para o vo e o mesmo apos o processo
     * para a camada DAO.
     */

    /**
     * contrutor de pais: Metodo que faz a construcao das telas e dos componentes necessarios
     * para a criacao da tela de pais
     */
    public gui_Pais() throws exception_RegistroJaExisteException{

        this.criaComps();
        this.montaComps();

        this.setTitle("Cadastro País");
        this.setBounds(100, 100, 300, 200);
        this.setResizable(false);
        this.setModal(true);
        this.setVisible(true);
    }

    /**
     * Contrutor de pais: Metodo que faz a contrucao das telas e dos componentes
     * necessarios para a criacao da tela pais e passa por parametro a conexao para
     * acesso ao banco
     * 
     * @param conn
     */
    gui_Pais(Connection conn) {
        this.criaComps();
        this.montaComps();
        
        this.setTitle("Cadastro Pais");
        this.setBounds(100, 100, 300, 200);
        this.setResizable(false);
        this.setModal(true);
        this.setVisible(true);

        this.conn = conn;
    }

    /**
     * cria componentes da tela grafica para a tela
     */
    public void criaComps(){
        //criando labels
        this.labelCodigo = new JLabel("Codigo");
        this.labelNome = new JLabel("Nome");


        NumberFormat numero;
        numero = NumberFormat.getNumberInstance();
        this.textCodigo = new JFormattedTextField(numero);

//        textCodigo.setValue(indice);
        textCodigo.setColumns(10);
        textCodigo.addPropertyChangeListener("value", this);

        this.textNome = new JTextField();

        //criando botoes
        this.buttonInserir = new JButton("Inserir");
        buttonInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    try {
                        buttonInserirActionPerformed(evt);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(gui_Pais.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(gui_Pais.class.getName()).log(Level.SEVERE, null, ex);
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
                        Logger.getLogger(gui_Pais.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(gui_Pais.class.getName()).log(Level.SEVERE, null, ex);
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
                        Logger.getLogger(gui_Pais.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(gui_Pais.class.getName()).log(Level.SEVERE, null, ex);
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
        this.add(this.labelCodigo);
        this.add(this.labelNome);

        //monta campos texto
        this.textCodigo.setBounds(60, 10, 150, 30);
        this.textNome.setBounds(60, 50, 150, 30);
        this.add(this.textCodigo);
        this.add(this.textNome);

        //monta botoes
        this.buttonInserir.setBounds(10, 100, 90, 30);
        this.buttonAlterar.setBounds(100, 100, 90, 30);
        this.buttonExcluir.setBounds(190, 100, 90, 30);
        this.buttonLimpar.setBounds(10, 130, 90, 30);
        this.buttonCancelar.setBounds(100, 130, 90, 30);
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
        codigo = ((Number)textCodigo.getValue()).intValue();
        
        nome = textNome.getText();
        vo_Pais pais = new vo_Pais();
        pais.setCodigo(codigo);
        pais.setNome(nome);
        
        dao_Pais banco = new dao_Pais(conn);
        banco.insere(pais);
        JOptionPane.showMessageDialog(new JOptionPane(), "Pais inserido com sucesso","Aviso!",JOptionPane.INFORMATION_MESSAGE);
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
        codigo = ((Number)textCodigo.getValue()).intValue();
        nome = textNome.getText();
        vo_Pais pais = new vo_Pais();
        pais.setCodigo(codigo);
        pais.setNome(nome);

        dao.dao_Pais banco = new dao.dao_Pais(conn);
        banco.altera(pais);
        JOptionPane.showMessageDialog(new JOptionPane(), "Pais alterado com sucesso","Aviso!",JOptionPane.INFORMATION_MESSAGE);
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
        codigo = ((Number)textCodigo.getValue()).intValue();
        nome = textNome.getText();

        vo_Pais pais = new vo_Pais();
        pais.setCodigo(codigo);
        pais.setNome(nome);

        dao.dao_Pais banco = new dao.dao_Pais(conn);
        banco.exclui(pais);
        JOptionPane.showMessageDialog(new JOptionPane(), "Pais excluido com sucesso","Aviso!",JOptionPane.INFORMATION_MESSAGE);
        limpa();
    }

    /**
     * limpa todos os campos da tela
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

    //metodo para fazer a critica do tab, e para acessar o banco, retornando
    // os bagulhos necessarios. Por aqui, fazer a consulta e mandar para cadastrar o banco
    //possivelmente acessar o banco e pegar o proximo codigo que deve ser usado para cadastro
    public void propertyChange(PropertyChangeEvent evt) {
        Object source = evt.getSource();
        if ((source == textCodigo)&&(textCodigo.getValue() != null)){
            String teste = "";
            teste = ((Number)textCodigo.getValue()).toString();
            try{
                aux = Integer.parseInt(teste);
                vo_Pais pais = new vo_Pais();
                pais.setCodigo(aux);
                try{
                    dao_Pais banco = null;
                    try {
                        banco = new dao_Pais();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(gui_Pais.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(gui_Pais.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        pais = banco.selecionaPais(pais);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(gui_Pais.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(gui_Pais.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    textNome.setText(pais.getNome());
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