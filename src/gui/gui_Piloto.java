package gui;
import dao.dao_Piloto;
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
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.Date;
import vo.vo_Piloto;

/**
 * Classe pronta nos padroes atuais
 * 
 * @author edipoterra
 */
public class gui_Piloto extends javax.swing.JDialog implements PropertyChangeListener{
    //cria label
    private JLabel labelCodigo;
    private JLabel labelNome;
    private JLabel labelPais;
    private JLabel labelEscuderia;
    private JLabel labelDtIniContr;
    private JLabel labelDtFimContr;

    //cria campo texto
    private JFormattedTextField textCodigo;
    private JTextField textNome;
    private JFormattedTextField textDtIni;
    private JFormattedTextField textDtFim;
    private JComboBox comboEquipe;
    private JComboBox comboPais;

    //cria botoes
    private JButton buttonInserir;
    private JButton buttonAlterar;
    private JButton buttonExcluir;
    private JButton buttonLimpar;
    private JButton buttonCancelar;

    //cria variaveis auxiliares
    private String[][] matriz1;
    private String[][] matriz2;
    private int aux;

    private Connection conn;
    
    /**
     * metodo construtor de piloto, montando a tela
     */
    public gui_Piloto() throws ClassNotFoundException, SQLException{
        this.criaComps();
        this.montaComps();

        this.setTitle("Cadastro Piloto");
        this.setBounds(100, 100, 300, 350);
        this.setResizable(false);
        this.setModal(true);
        this.setVisible(true);
    }

    /**
     * metodo construtor de piloto com conexao
     * @param conn
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    gui_Piloto(Connection conn) throws ClassNotFoundException, SQLException {
        this.criaComps();
        this.montaComps();
        
        this.setTitle("Cadastro Piloto");
        this.setBounds(100, 100, 300, 350);
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
        this.labelCodigo = new JLabel("Codigo");
        this.labelNome = new JLabel("Nome");
        this.labelPais = new JLabel("País");
        this.labelEscuderia = new JLabel("Equipe");
        this.labelDtIniContr = new JLabel("Inicio Contrato");
        this.labelDtFimContr = new JLabel("Fim Contrato");

        //criando campos de texto
        MaskFormatter format_txt_dtini, format_txt_dtfim;
        NumberFormat numero;
        numero = NumberFormat.getNumberInstance();
        
        this.textCodigo = new JFormattedTextField(numero);
        textCodigo.setColumns(10);
        textCodigo.addPropertyChangeListener("value", this);

        try{
            format_txt_dtini = new MaskFormatter("##/##/####");
            this.textDtIni = new JFormattedTextField(format_txt_dtini);
            format_txt_dtfim = new MaskFormatter("##/##/####");
            this.textDtFim = new JFormattedTextField(format_txt_dtfim);

        } catch (ParseException ex){
            ex.printStackTrace();
        }
        this.textNome = new JTextField();

        //criando combo
        dao_Piloto esc = new dao_Piloto(conn);
        try {
            matriz1 = esc.selectPais();
        } catch (exception_RegistroJaExisteException ex) {
            JOptionPane.showMessageDialog(new JOptionPane(), ex.getMessage(),"Ops...",JOptionPane.ERROR_MESSAGE);
        }
        this.comboPais = new JComboBox(matriz1[1]);
        try {
            matriz2 = esc.selectEquipe();
        } catch (exception_RegistroJaExisteException ex) {
            JOptionPane.showMessageDialog(new JOptionPane(), ex.getMessage(),"Ops...",JOptionPane.ERROR_MESSAGE);
        }
        this.comboEquipe = new JComboBox(matriz2[1]);

        //criando botoes
        this.buttonInserir = new JButton("Inserir");
        buttonInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    try {
                        buttonInserirActionPerformed(evt);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(gui_Piloto.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(gui_Piloto.class.getName()).log(Level.SEVERE, null, ex);
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
                        Logger.getLogger(gui_Piloto.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(gui_Piloto.class.getName()).log(Level.SEVERE, null, ex);
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
                        Logger.getLogger(gui_Piloto.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(gui_Piloto.class.getName()).log(Level.SEVERE, null, ex);
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
        this.labelCodigo.setBounds(10, 20, 100, 15);
        this.labelNome.setBounds(10, 60, 100, 15);
        this.labelPais.setBounds(10, 100, 100, 15);
        this.labelEscuderia.setBounds(10, 140, 100, 15);
        this.labelDtIniContr.setBounds(10, 180, 100, 15);
        this.labelDtFimContr.setBounds(10, 220, 100, 15);
        this.add(this.labelCodigo);
        this.add(this.labelNome);
        this.add(this.labelPais);
        this.add(this.labelEscuderia);
        this.add(this.labelDtIniContr);
        this.add(this.labelDtFimContr);

        //monta campos texto
        this.textCodigo.setBounds(120, 10, 150, 30);
        this.textNome.setBounds(120, 50, 150, 30);
        this.comboPais.setBounds(120, 90,150, 30);
        this.comboEquipe.setBounds(120, 130, 150, 30);
        this.textDtIni.setBounds(120, 170, 150, 30);
        this.textDtFim.setBounds(120, 210, 150, 30);
        this.add(this.textCodigo);
        this.add(this.textNome);
        this.add(this.comboPais);
        this.add(this.comboEquipe);
        this.add(this.textDtIni);
        this.add(this.textDtFim);

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
        int codEquipe = 0;

        //encontra campos dia, mes e ano
        String aux1 = textDtIni.getText();

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

        Date dtIni = new Date();
        dtIni.setDate(diaI);
        dtIni.setMonth(mesI - 1);
        dtIni.setYear(anoI - 1900);

        //encontra campos dia, mes e ano
        String aux3 = textDtIni.getText();

        char[] aux4 = aux3.toCharArray();

        //vetor dia
        char[] dia2 = new char[2];
        dia2[0] = aux4[0];
        dia2[1] = aux4[1];
        String dia2S = String.copyValueOf(dia2);

        //vetor mes
        char[] mes2 = new char[2];
        mes2[0] = aux4[3];
        mes2[1] = aux4[4];
        String mes2S = String.copyValueOf(mes2);

        //vetor ano
        char[] ano2 = new char[4];
        ano2[0] = aux4[6];
        ano2[1] = aux4[7];
        ano2[2] = aux4[8];
        ano2[3] = aux4[9];
        String ano2S = String.copyValueOf(ano2);

        int diaF = Integer.parseInt(dia2S);
        int mesF = Integer.parseInt(mes2S);
        int anoF = Integer.parseInt(ano2S);

        Date dtFim = new Date();
        dtIni.setDate(diaF);
        dtIni.setMonth(mesF - 1);
        dtIni.setYear(anoF - 1900);

        codigo = ((Number)textCodigo.getValue()).intValue();
        nome = textNome.getText();

        codPais = Integer.parseInt(matriz1[0][comboPais.getSelectedIndex()]);

        codEquipe = Integer.parseInt(matriz2[0][comboEquipe.getSelectedIndex()]);

        vo.vo_Contrato cont = new vo.vo_Contrato();
        cont.setDataInicial(dtIni);
        cont.setDataFinal(dtFim);
        vo_Piloto piloto = new vo_Piloto();
        piloto.setCodigo(codigo);
        piloto.setNome(nome);
        piloto.setPais(codPais);
        piloto.setEquipe(codEquipe);
        piloto.setContrato(cont);

        dao.dao_Piloto banco = new dao.dao_Piloto(conn);
        banco.insere(piloto);
        JOptionPane.showMessageDialog(new JOptionPane(), "Piloto inserido com sucesso","Aviso!.",JOptionPane.INFORMATION_MESSAGE);
        limpa();
    }

    /**
     * metodo pega os dados dos campos da tela e manda para a camada de negocio, no fim
     * caso nao ocorrer excecao, retorna uma mensagem de processo OK
     * Faz umas movimentacoes para pegar a data correta para inserir em piloto
     *
     * @throws exception_RegistroJaExisteException
     */
    public void altera() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        int codigo = 0;
        String nome = "";
        int codPais = 0;
        int codEquipe = 0;

        /**
         * Parte do codigo faz a movimentacao correta para selecionar de forma correta
         * as data inicial recebidas pelo campo de texto da tela
         */
        String aux1 = textDtIni.getText();
        
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

        Date dtIni = new Date();
        dtIni.setDate(diaI);
        dtIni.setMonth(mesI - 1);
        dtIni.setYear(anoI - 1900);

        /**
         * Parte do codigo que recebe de forma correta a data final do contrato
         * para mandar para o banco de dados.
         */
        String aux3 = textDtIni.getText();

        char[] aux4 = aux3.toCharArray();

        //vetor dia
        char[] dia2 = new char[2];
        dia2[0] = aux4[0];
        dia2[1] = aux4[1];
        String dia2S = String.copyValueOf(dia2);

        //vetor mes
        char[] mes2 = new char[2];
        mes2[0] = aux4[3];
        mes2[1] = aux4[4];
        String mes2S = String.copyValueOf(mes2);

        //vetor ano
        char[] ano2 = new char[4];
        ano2[0] = aux4[6];
        ano2[1] = aux4[7];
        ano2[2] = aux4[8];
        ano2[3] = aux4[9];
        String ano2S = String.copyValueOf(ano2);

        int diaF = Integer.parseInt(dia2S);
        int mesF = Integer.parseInt(mes2S);
        int anoF = Integer.parseInt(ano2S);

        Date dtFim = new Date();
        dtIni.setDate(diaF);
        dtIni.setMonth(mesF - 1);
        dtIni.setYear(anoF - 1900);

        codigo = ((Number)textCodigo.getValue()).intValue();
        nome = textNome.getText();
        codPais = Integer.parseInt(matriz1[0][comboPais.getSelectedIndex()]);

        codEquipe = Integer.parseInt(matriz2[0][comboEquipe.getSelectedIndex()]);

        vo.vo_Contrato cont = new vo.vo_Contrato();
        cont.setDataInicial(dtIni);
        cont.setDataFinal(dtFim);
        vo_Piloto piloto = new vo_Piloto();
        piloto.setCodigo(codigo);
        piloto.setNome(nome);
        piloto.setPais(codPais);
        piloto.setEquipe(codEquipe);
        piloto.setContrato(cont);

        dao.dao_Piloto banco = new dao.dao_Piloto(conn);
        banco.altera(piloto);

        JOptionPane.showMessageDialog(new JOptionPane(), "Piloto alterado com sucesso","Aviso!",JOptionPane.INFORMATION_MESSAGE);
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
        int codEquipe = 0;

        codigo = ((Number)textCodigo.getValue()).intValue();

        nome = textNome.getText();
        
        codPais = Integer.parseInt(matriz1[0][comboPais.getSelectedIndex()]);

        codEquipe = Integer.parseInt(matriz2[0][comboEquipe.getSelectedIndex()]);

        vo_Piloto piloto = new vo_Piloto();
        piloto.setCodigo(codigo);
        piloto.setNome(nome);
        piloto.setPais(codPais);
        piloto.setEquipe(codEquipe);

        dao.dao_Piloto banco = new dao.dao_Piloto(conn);
        banco.exclui(piloto);
        JOptionPane.showMessageDialog(new JOptionPane(), "Piloto excluido com sucesso","Aviso!",JOptionPane.INFORMATION_MESSAGE);
        limpa();
    }

    /**
     * limpa todos os campos da tela, exceto combos
     */
    public void limpa(){
        textCodigo.setText("");
        textNome.setText("");
        textDtIni.setText("");
        textDtFim.setText("");
    }

    /**
     * fecha a tela sem salvar
     */
    public void cancela(){
        this.hide();
    }

    /**
     * campo responsavel pelos campos de texto
     * @param evt
     */
    public void propertyChange(PropertyChangeEvent evt) {
        Object source = evt.getSource();
        if ((source == textCodigo)&&(textCodigo.getValue() != null)){
            String teste = "";
            teste = ((Number)textCodigo.getValue()).toString();
            try{
                aux = Integer.parseInt(teste);
                vo_Piloto piloto = new vo_Piloto();
                piloto.setCodigo(aux);
                try{
                    dao_Piloto banco = new dao_Piloto(conn);
                    try {
                        piloto = banco.selecionaPiloto(piloto);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(gui_Piloto.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(gui_Piloto.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (piloto != null && piloto.getNome() != null){
                        textNome.setText(piloto.getNome());
                        int index = 0;
                        boolean achou;
                        for (int i = 0; i < matriz1[0].length; i++){
                            if (Integer.parseInt(matriz1[0][i]) == piloto.getPais()){
                                index = i;
                                achou = true;
                                break;
                            }
                        }
                        comboPais.setSelectedIndex(index);
                        index = 0;
                        achou = false;
                        for (int i = 0; i < matriz2[0].length; i++){
                            if (Integer.parseInt(matriz2[0][i]) == piloto.getEquipe()){
                                index = i;
                                achou = true;
                                break;
                            }
                        }
                        comboEquipe.setSelectedIndex(index);
  
                        textDtIni.setText(piloto.getContrato().getDataInicial().toLocaleString());
                        textDtFim.setText(piloto.getContrato().getDataFinal().toLocaleString());
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
    }
}