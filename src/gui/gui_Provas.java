package gui;
import dao.dao_Provas;
import dao.exception_RegistroJaExisteException;
import java.beans.PropertyChangeEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import vo.vo_Corrida;
/**
 * Classe que cria a tela de cadastro de provas
 *
 * @author edipoterra
 */
public class gui_Provas extends javax.swing.JDialog implements PropertyChangeListener{
    //declaracao labels
    private JLabel labelCodigo;
    private JLabel labelNome;
    private JLabel labelPista;
    private JLabel labelPais;
    private JLabel labelVoltas;
    private JLabel labelData;

    //declaracao campos texto
    private JFormattedTextField textCodigo;

    private JTextField textNome;
    private JComboBox comboPista;
    private JComboBox comboPais;

    private JFormattedTextField textVoltas;
    private JFormattedTextField textData;

    private JButton buttonInserir;
    private JButton buttonAlterar;
    private JButton buttonExcluir;
    private JButton buttonLimpar;
    private JButton buttonCancelar;

    private String[][] matriz1;
    private String[][] matriz2;
    private int aux;

    private Connection conn;

    /**
     * metodo construtor de provas
     */
    public gui_Provas() throws ClassNotFoundException, SQLException{
        this.criaComps();
        this.montaComps();

        this.setTitle("Cadastro Provas");
        this.setBounds(100, 100, 300, 350);
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
    gui_Provas(Connection conn) throws ClassNotFoundException, SQLException {
        this.criaComps();
        this.montaComps();

        this.setTitle("Cadastro Provas");
        this.setBounds(100, 100, 300, 350);
        this.setResizable(false);
        this.setModal(true);
        this.setVisible(true);

        this.conn = conn;
    }

    /**
     * metodo cria componentes
     */
    public void criaComps() throws ClassNotFoundException, SQLException{
        //criando labels
        this.labelCodigo = new JLabel("Codigo");
        this.labelNome = new JLabel("Nome");
        this.labelPista = new JLabel("Pista");
        this.labelPais = new JLabel("País");
        this.labelVoltas = new JLabel("Voltas");
        this.labelData = new JLabel("Data");

        //criando campos de texto
        NumberFormat numero;
        numero = NumberFormat.getNumberInstance();
        this.textCodigo = new JFormattedTextField(numero);
        textCodigo.setColumns(10);
        textCodigo.addPropertyChangeListener("value", this);

        this.textNome = new JTextField();

        NumberFormat numero1;
        numero1 = NumberFormat.getNumberInstance();
        this.textVoltas = new JFormattedTextField(numero1);
        textVoltas.setColumns(10);
        textVoltas.addPropertyChangeListener("value", this);

        MaskFormatter format_txt_dtprova;
        try{
            format_txt_dtprova = new MaskFormatter("##/##/####");
            this.textData = new JFormattedTextField(format_txt_dtprova);


        } catch (ParseException ex){
            ex.printStackTrace();
        }

        dao.dao_Provas prova = new dao.dao_Provas(conn);
        try {
            matriz1 = prova.selectPista();
        } catch (exception_RegistroJaExisteException ex) {
            JOptionPane.showMessageDialog(new JOptionPane(), ex.getMessage(),"Ops...",JOptionPane.ERROR_MESSAGE);
        }
        this.comboPista = new JComboBox(matriz1[1]);
        try {
            matriz2 = prova.selectPais();
        } catch (exception_RegistroJaExisteException ex) {
            JOptionPane.showMessageDialog(new JOptionPane(), ex.getMessage(),"Ops...",JOptionPane.ERROR_MESSAGE);
        }

        this.comboPais = new JComboBox(matriz2[1]);

        //criando botoes
        this.buttonInserir = new JButton("Inserir");
        buttonInserir.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                try {
                    try {
                        buttonInserirActionPerformed(evt);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(gui_Provas.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(gui_Provas.class.getName()).log(Level.SEVERE, null, ex);
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
                        Logger.getLogger(gui_Provas.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(gui_Provas.class.getName()).log(Level.SEVERE, null, ex);
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
                        Logger.getLogger(gui_Provas.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(gui_Provas.class.getName()).log(Level.SEVERE, null, ex);
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
     * metodo monta os componentes, com suas dimensoes e coisas do genero e coloca na
     * tela
     */
    public void montaComps(){
        //monta labels
        this.labelCodigo.setBounds(10, 20, 100, 15);
        this.labelNome.setBounds(10, 60, 100, 15);
        this.labelPista.setBounds(10, 100, 100, 15);
        this.labelPais.setBounds(10, 140, 100, 15);
        this.labelVoltas.setBounds(10, 180, 100, 15);
        this.labelData.setBounds(10, 220, 100, 15);
        this.add(this.labelCodigo);
        this.add(this.labelNome);
        this.add(this.labelPista);
        this.add(this.labelPais);
        this.add(this.labelVoltas);
        this.add(this.labelData);

        //monta campos texto
        this.textCodigo.setBounds(120, 10, 150, 30);
        this.textNome.setBounds(120, 50, 150, 30);
        this.comboPista.setBounds(120, 90, 150, 30);
        this.comboPais.setBounds(120, 130,150, 30);
        this.textVoltas.setBounds(120, 170, 150, 30);
        this.textData.setBounds(120, 210, 150, 30);
        this.add(this.textCodigo);
        this.add(this.textNome);
        this.add(this.comboPista);
        this.add(this.comboPais);
        this.add(this.textVoltas);
        this.add(this.textData);

        //monta botoes
        this.buttonInserir.setBounds(10, 250, 90, 30);
        this.buttonAlterar.setBounds(100, 250, 90, 30);
        this.buttonExcluir.setBounds(190, 250, 90, 30);
        this.buttonLimpar.setBounds(10, 280, 90, 30);
        this.buttonCancelar.setBounds(100, 280, 90, 30);
        this.add(this.buttonInserir);
        this.add(this.buttonAlterar);
        this.add(this.buttonExcluir);
        this.add(this.buttonLimpar);
        this.add(this.buttonCancelar);
    }

    /**
     * acao para o botao inserir
     *
     * @param evt
     * @throws exception_RegistroJaExisteException
     */
    public void buttonInserirActionPerformed(java.awt.event.ActionEvent evt) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        insere();
    }

    /**
     * acao para o botao alterar
     *
     * @param evt
     * @throws exception_RegistroJaExisteException
     */
    public void buttonAlterarActionPerformed(java.awt.event.ActionEvent evt) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        altera();
    }

    /**
     * acao para o botao excluir
     *
     * @param evt
     * @throws exception_RegistroJaExisteException
     */
    public void buttonExcluirActionPerformed(java.awt.event.ActionEvent evt) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        exclui();
    }

    /**
     * acao para o botao limpar
     *
     * @param evt
     */
    public void buttonLimparActionPerformed(java.awt.event.ActionEvent evt){
        limpa();
    }

    /**
     * acao para o botao cancelar
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
        int codigo;
        int numVoltas = 0;
        String nome = textNome.getText();

//+---------------------------------------------------+
        String aux1 = textData.getText();
        
        char[] aux2 = aux1.toCharArray();
        
        //vetor dia
        char[] dia = new char[2];
        dia[0] = aux2[0];
        dia[1] = aux2[1];
        String diaS = String.copyValueOf(dia);
        
        //vetor mes
        char[] mes = new char[2];
        mes[0] = aux2[3];
        mes[1] = aux2[4];
        String mesS = String.copyValueOf(mes);

        //vetor ano
        char[] ano = new char[4];
        ano[0] = aux2[6];
        ano[1] = aux2[7];
        ano[2] = aux2[8];
        ano[3] = aux2[9];
        String anoS = String.copyValueOf(ano);

        int diaI = Integer.parseInt(diaS);
        int mesI = Integer.parseInt(mesS);
        int anoI = Integer.parseInt(anoS);

        Date data = new Date();
        data.setDate(diaI);
        data.setMonth(mesI - 1);
        data.setYear(anoI - 1900);
//+---------------------------------------------------+

        codigo = ((Number)textCodigo.getValue()).intValue();
        numVoltas = ((Number)textVoltas.getValue()).intValue();

        int codPista;
        codPista = Integer.parseInt(matriz1[0][comboPista.getSelectedIndex()]);

        int codPais = 0;

        codPais = Integer.parseInt(matriz2[0][comboPais.getSelectedIndex()]);

        vo_Corrida corrida = new vo_Corrida();
        corrida.setEtapa(codigo);
        corrida.setNome(nome);
        corrida.setCodPista(codPista);
        corrida.setCodPais(codPais);
        corrida.setNum_voltas(numVoltas);
        corrida.setDiaCorrida(data);

        dao_Provas prova = new dao_Provas(conn);
        prova.insere(corrida);
        
        JOptionPane.showMessageDialog(new JOptionPane(), "Prova inserida com sucesso","Aviso!",JOptionPane.INFORMATION_MESSAGE);
        limpa();
    }

    /**
     * metodo pega os dados dos campos da tela e manda para a camada de negocio, no fim
     * caso nao ocorrer excecao, retorna uma mensagem de processo OK
     *
     * @throws exception_RegistroJaExisteException
     */
    public void altera() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        int codigo;
        int numVoltas = 0;
        String nome = textNome.getText();

//+---------------------------------------------------+
        String aux1 = textData.getText();

        char[] aux2 = aux1.toCharArray();

        //vetor dia
        char[] dia = new char[2];
        dia[0] = aux2[0];
        dia[1] = aux2[1];
        String diaS = String.copyValueOf(dia);

        //vetor mes
        char[] mes = new char[2];
        mes[0] = aux2[3];
        mes[1] = aux2[4];
        String mesS = String.copyValueOf(mes);

        //vetor ano
        char[] ano = new char[4];
        ano[0] = aux2[6];
        ano[1] = aux2[7];
        ano[2] = aux2[8];
        ano[3] = aux2[9];
        String anoS = String.copyValueOf(ano);

        int diaI = Integer.parseInt(diaS);
        int mesI = Integer.parseInt(mesS);
        int anoI = Integer.parseInt(anoS);

        Date data = new Date();
        data.setDate(diaI);
        data.setMonth(mesI - 1);
        data.setYear(anoI - 1900);
//+---------------------------------------------------+

        codigo = ((Number)textCodigo.getValue()).intValue();
        numVoltas = ((Number)textVoltas.getValue()).intValue();

        int codPista;
        codPista = Integer.parseInt(matriz1[0][comboPista.getSelectedIndex()]);

        int codPais = 0;

        codPais = Integer.parseInt(matriz2[0][comboPais.getSelectedIndex()]);

        vo_Corrida corrida = new vo_Corrida();
        corrida.setEtapa(codigo);
        corrida.setNome(nome);
        corrida.setCodPista(codPista);
        corrida.setCodPais(codPais);
        corrida.setNum_voltas(numVoltas);
        corrida.setDiaCorrida(data);

        dao_Provas prova = new dao_Provas(conn);
        prova.altera(corrida);

        JOptionPane.showMessageDialog(new JOptionPane(), "Prova alterada com sucesso","Aviso!",JOptionPane.INFORMATION_MESSAGE);
        limpa();    }

    /**
     * metodo pega os dados dos campos da tela e manda para a camada de negocio, no fim
     * caso nao ocorrer excecao, retorna uma mensagem de processo OK
     *
     * @throws exception_RegistroJaExisteException
     */
    public void exclui() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        int codigo;
        int numVoltas = 0;
        String nome = textNome.getText();

        codigo = ((Number)textCodigo.getValue()).intValue();
        numVoltas = ((Number)textVoltas.getValue()).intValue();
                //Integer.parseInt(textVoltas.getText());

        int codPista;
        codPista = Integer.parseInt(matriz1[0][comboPista.getSelectedIndex()]);

        int codPais = 0;

        codPais = Integer.parseInt(matriz2[0][comboPais.getSelectedIndex()]);

        vo_Corrida corrida = new vo_Corrida();
        corrida.setEtapa(codigo);
        corrida.setNome(nome);
        corrida.setCodPista(codPista);
        corrida.setCodPais(codPais);
        corrida.setNum_voltas(numVoltas);
        corrida.setDiaCorrida(null);

        dao_Provas prova = new dao_Provas(conn);
        prova.exclui(corrida);

        JOptionPane.showMessageDialog(new JOptionPane(), "Prova excluida com sucesso","Aviso!",JOptionPane.INFORMATION_MESSAGE);
        limpa();    }

    /**
     * metodo limpa todos os dados dos campos, exceto os comboboxes
     */
    public void limpa(){
        textCodigo.setText("");
        textVoltas.setText("");
        textData.setText("");
    }

    /**
     * metodo fecha a tela, sem salvar nada
     */
    public void cancela(){
        this.hide();
    }

    /**
     * verifica os campos de texto
     * @param evt
     */
    public void propertyChange(PropertyChangeEvent evt) {
        Object source = evt.getSource();
        if ((source == textCodigo)&&(textCodigo.getValue() != null)){
            String teste = "";
            teste = ((Number)textCodigo.getValue()).toString();
            try{
                aux = Integer.parseInt(teste);
                vo_Corrida corrida = new vo_Corrida();
                corrida.setEtapa(aux);
                try{
                    dao_Provas banco = new dao_Provas(conn);
                    try {
                        corrida = banco.selecionaProva(corrida);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(gui_Provas.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(gui_Provas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if ((corrida != null)&&(corrida.getNome()!= null)){
                        textNome.setText(corrida.getNome());
                        int index = 0;
                        boolean achou;
                        for (int i = 0; i < matriz1[0].length; i++){
                            if (Integer.parseInt(matriz1[0][i]) == corrida.getCodPista()){
                                index = i;
                                achou = true;
                                break;
                            }
                        }
                        comboPista.setSelectedIndex(index);
                        index = 0;
                        achou = false;
                        for (int i = 0; i < matriz2[0].length; i++){
                            if (Integer.parseInt(matriz2[0][i]) == corrida.getCodPais()){
                                index = i;
                                achou = true;
                                break;
                            }
                        }
                        comboPais.setSelectedIndex(index);

                        textVoltas.setText(String.valueOf(corrida.getNum_voltas()));
                        textData.setText(corrida.getDiaCorrida().toLocaleString());
                    }
                }
                catch (exception_RegistroJaExisteException ex){
                    JOptionPane.showMessageDialog(new JOptionPane(), ex.getMessage(),"Ops...",JOptionPane.ERROR_MESSAGE);

                }

            }
            catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(new JOptionPane(), "A quantidade de digitos exedeu o limite!","Ops...",JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            if ((source == textVoltas)&&(textVoltas.getValue() != null)){
                String teste = "";
                teste = ((Number)textVoltas.getValue()).toString();
                try{
                    aux = Integer.parseInt(teste);
                }
                catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(new JOptionPane(), "A quantidade de digitos exedeu o limite!","Ops...",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}