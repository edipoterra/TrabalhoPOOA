package gui;
import dao.exception_RegistroJaExisteException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

    /**
     * Esta classe vai conter a tela principal do programa, referencias as outras
     * telas. A linkagem com outras telas se da pelo menu, que fica exclusivamente,
     * ou quase que esclusivamente nessa tela.
     *
     * @author Edipo Deon Terra
     *
     */
public class gui_TelaPrincipal extends JFrame implements java.awt.event.KeyListener{

    private JMenuBar menu;
    private JMenu menuArquivo;
    private JMenu menuCadastro;
    private JMenu menuPesquisa;
    private JMenu menuParticipante;
    private JMenu menuGrid;
    private JMenu menuGrafico;
    private JMenuItem itemSair;
    private JMenuItem itemPais;
    private JMenuItem itemPista;
    private JMenuItem itemEscuderia;
    private JMenuItem itemPiloto;
    private JMenuItem itemProvas;
    private JMenuItem itemGridChegada;
    private JMenuItem itemGridLargada;
    private JMenuItem itemProvaPiloto;
    private JMenuItem itemProvaGridLargada;
    private JMenuItem itemProvaGridChegada;
    private JMenuItem itemPontosPiloto;
    private JMenuItem itemPontosEquipe;
    private JMenuItem itemRelacaoPiloto;
    private JMenuItem itemRelacaoEscuderia;
    private JMenuItem itemRelacaoProva;
    private JMenuItem itemGraficoPontoPiloto;
    private JMenuItem itemGraficoEvolPiloto;
    private JMenuItem itemGraficoPontosEquipe;
    private JMenuItem itemGraficoEvolEquipe;
    private Connection conn;

    /**
     * metodo cria tela principal do programa com um nome padrao
     */
    public gui_TelaPrincipal() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        this.setTitle("Campeonato de Formula1");
        this.setSize(500, 400);
        this.setResizable(false);
        conn = dao.dao_GeraConnection.getConexao();
    }

    /**
     * metodo cria a tela principal com um nome passado por parametro
     *
     * @param nomeTela
     */
    public gui_TelaPrincipal(String nomeTela) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        JFrame janela = new JFrame(nomeTela);
        janela.setSize(500, 400);
        janela.setVisible(true);
        conn = dao.dao_GeraConnection.getConexao();
    }

    /**
     * metodo cria o menu e os listener para manipular as chamadas do menu
     */
    public void criaMenu(){
        this.menu = new JMenuBar();

        this.menuArquivo = new JMenu("Arquivo");
        this.itemSair = new JMenuItem("Sair");
        itemSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.exit(0);
            }
        });

        this.menuCadastro = new JMenu("Cadastro");
        this.itemPais = new JMenuItem("Pais");
        itemPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    itemPaisActionPerformed(evt);
                } catch (exception_RegistroJaExisteException ex) {
                    JOptionPane.showMessageDialog(new JOptionPane(), ex.getMessage(),"Ops...",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        this.itemPista = new JMenuItem("Pista");
        itemPista.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                try {
                    itemPistaActionPerformed(evt);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        this.menuParticipante = new JMenu("Participante");
        this.itemEscuderia = new JMenuItem("Escuderia");
        itemEscuderia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    itemEscuderiaActionPerformed(evt);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.itemPiloto = new JMenuItem("Piloto");
        itemPiloto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    itemPilotoActionPerformed(evt);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        this.itemProvas = new JMenuItem("Provas");
        itemProvas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    itemProvasActionPerformed(evt);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        this.menuGrid = new JMenu("Grid");
        this.itemGridChegada = new JMenuItem("Grid Chegada");
        itemGridChegada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    itemGridChegadaActionPerformed(evt);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        this.itemGridLargada = new JMenuItem("Grid Largada");
        itemGridLargada.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                try {
                    itemGridLargadaActionPerformed(evt);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.menuPesquisa = new JMenu("Selecionar");
        this.itemProvaPiloto = new JMenuItem("Prova X Piloto");
        itemProvaPiloto.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                try {
                    itemProvaPilotoActionPerformed(evt);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        this.itemProvaGridLargada = new JMenuItem("Resultado com Largada");
        itemProvaGridLargada.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                try{
                    itemResultadoLargada(evt);
                } catch (ClassNotFoundException ex){
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex){
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        this.itemProvaGridChegada = new JMenuItem("Resultado com Chegada");
        itemProvaGridChegada.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                try{
                    itemResultadoChegada(evt);
                } catch (ClassNotFoundException ex){
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex){
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.itemPontosPiloto = new JMenuItem("Pontuacao Pilotos");
        itemPontosPiloto.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                try {
                    itemPontosPiloto(evt);
                } catch (exception_RegistroJaExisteException ex) {
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                
                } catch (ClassNotFoundException ex){
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex){
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.itemPontosEquipe = new JMenuItem("Pontuacao Equipe");
        itemPontosEquipe.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                try{
                    itemPontosEquipe(evt);
                } catch (exception_RegistroJaExisteException ex){
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex){
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex){
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.itemRelacaoPiloto = new JMenuItem("Relação de Pilotos");
        itemRelacaoPiloto.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                try{
                    itemRelacaoPiloto(evt);
                } catch (exception_RegistroJaExisteException ex) {
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex){
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex){
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.itemRelacaoEscuderia = new JMenuItem("Relação de Escuderia");
        itemRelacaoEscuderia.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                try{
                    itemRelacaoEquipe(evt);
                } catch (exception_RegistroJaExisteException ex){
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex){
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex){
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.itemRelacaoProva = new JMenuItem("Relação de Provas");
        itemRelacaoProva.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                try{
                    itemRelacaoProvas(evt);
                } catch (exception_RegistroJaExisteException ex){
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex){
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex){
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        this.menuGrafico = new JMenu("Grafico");
        this.itemGraficoPontoPiloto = new JMenuItem("Pontos Pilotos");
        itemGraficoPontoPiloto.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                try{
                    itemGraficoPiloto(evt);
                } catch (exception_RegistroJaExisteException ex){
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex){
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex){
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.itemGraficoEvolPiloto = new JMenuItem("Evolução dos Pilotos");
        itemGraficoEvolPiloto.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                try{
                    itemGraficoEvPiloto(evt);
                } catch (exception_RegistroJaExisteException ex){
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex){
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex){
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex){
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.itemGraficoPontosEquipe = new JMenuItem("Pontos Equipes");
        itemGraficoPontosEquipe.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                try{
                    itemGraficoEquipe(evt);
                } catch (exception_RegistroJaExisteException ex){
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex){
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex){
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.itemGraficoEvolEquipe = new JMenuItem("Evolução das Equipes");
        itemGraficoEvolEquipe.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                try{
                    itemGraficoEvEquipe(evt);
                } catch (exception_RegistroJaExisteException ex){
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex){
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex){
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex){
                    Logger.getLogger(gui_TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });


        this.menuGrid.add(itemGridChegada);
        this.menuGrid.add(itemGridLargada);
        this.menuParticipante.add(itemEscuderia);
        this.menuParticipante.add(itemPiloto);

        this.menuCadastro.add(itemPais);
        this.menuCadastro.add(itemPista);
        this.menuCadastro.add(menuParticipante);
        this.menuCadastro.add(itemProvas);
        this.menuCadastro.add(menuGrid);

        this.menuPesquisa.add(itemProvaPiloto);
        this.menuPesquisa.add(itemProvaGridLargada);
        this.menuPesquisa.add(itemProvaGridChegada);
        this.menuPesquisa.add(itemPontosPiloto);
        this.menuPesquisa.add(itemPontosEquipe);
        this.menuPesquisa.add(itemRelacaoPiloto);
        this.menuPesquisa.add(itemRelacaoEscuderia);
        this.menuPesquisa.add(itemRelacaoProva);

        this.menuGrafico.add(itemGraficoPontoPiloto);
        this.menuGrafico.add(itemGraficoEvolPiloto);
        this.menuGrafico.add(itemGraficoPontosEquipe);
        this.menuGrafico.add(itemGraficoEvolEquipe);

        this.menuArquivo.add(itemSair);

        this.menu.add(menuArquivo);
        this.menu.add(menuCadastro);
        this.menu.add(menuPesquisa);
        this.menu.add(menuGrafico);
        this.setJMenuBar(this.menu);

        getContentPane().setLayout(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Metodo principal para rodar o programa
     *
     * @param args
     */
    public static void main(String[] args) throws IOException, exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        Process p = Runtime.getRuntime().exec("rodaBanco");
        gui_TelaPrincipal tela = new gui_TelaPrincipal();
        tela.criaMenu();
        tela.setVisible(true);
    }

    /**
     * metodo necessario para alguma tarefa ao ser pressionado alguma tecla.
     * Metodo da heranca do JFrame
     *
     * @param e
     */
    public void keyTyped(KeyEvent e) {
        //espaco para o codigo whatever
    }

    /**
     * Metodo necessario de ser implemetado pela heranca do JFrame.
     * Nessa implementacao, quando for pressionada a tecla F3, sera finalizado
     * o programa
     *
     * @param e
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_F3)
            System.exit(0);
    }

    /**
     * Metodo necessario pela heranca da classe JFrame. Este metodo se refere a
     * acao de soltar uma tecla
     *
     * @param e
     */
    public void keyReleased(KeyEvent e) {
        //espaco para o codigo whatever
    }

    /**
     * metodo instancia um pais
     */
    public void criaPais() throws exception_RegistroJaExisteException{
        gui_Pais pais = new gui_Pais(conn);
    }

    /**
     * metodo instancia uma pista
     */
    public void criaPista() throws ClassNotFoundException, SQLException{
        gui_Pista pista = new gui_Pista(conn);
    }

    /**
     * metodo instancia uma escuderia
     */
    public void criaEscuderia() throws ClassNotFoundException, SQLException{
        gui_Escuderia escuderia = new gui_Escuderia(conn);
    }

    /**
     * metodo instancia um piloto
     */
    public void criaPiloto() throws ClassNotFoundException, SQLException{
        gui_Piloto piloto = new gui_Piloto(conn);
    }

    /**
     * metodo instancia uma prova
     */
    public void criaProvas() throws ClassNotFoundException, SQLException{
        gui_Provas provas = new gui_Provas(conn);
    }

    /**
     * metodo instancia um grid de largada
     */
    public void criaGridLargada() throws ClassNotFoundException, SQLException{
        gui_GridLargada largada = new gui_GridLargada(conn);
    }

    /**
     * metodo instancia um grid de chegada
     */
    public void criaGridChegada() throws ClassNotFoundException, SQLException{
        gui_GridChegada chegada = new gui_GridChegada(conn);
    }

    /**
     * Metodo instancia tela pilotoProva
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void criaPilotoProva() throws ClassNotFoundException, SQLException{
        gui_PilotoProva pilotoProva = new gui_PilotoProva(conn);
    }

    /**
     * metodo instancia tela ResultLargada
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void criaResultLargada() throws ClassNotFoundException, SQLException{
        gui_ResultLargada resultLargada = new gui_ResultLargada(conn);
    }

    /**
     * Metodo instancia tela ResultChegada
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void criaResultChegada() throws ClassNotFoundException, SQLException{
        gui_ResultChegada resultChegada = new gui_ResultChegada(conn);
    }

    /**
     * metodo instancia tela PontosPiloto
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws exception_RegistroJaExisteException
     */
    public void criaPontosPiloto() throws ClassNotFoundException, SQLException, exception_RegistroJaExisteException{
        gui_PontosPiloto pontosPiloto = new gui_PontosPiloto(conn);
    }

    /**
     * metodo instancia tela PontosEquipe
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws exception_RegistroJaExisteException
     */
    public void criaPontosEquipe() throws ClassNotFoundException, SQLException, exception_RegistroJaExisteException{
        gui_PontosEquipe pontosEquipe = new gui_PontosEquipe(conn);
    }

    /**
     * Metodo instancia tela RelacaoPiloto
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws exception_RegistroJaExisteException
     */
    public void criaRelacaoPiloto() throws ClassNotFoundException, SQLException, exception_RegistroJaExisteException{
        gui_RelacaoPilotos relacaoEquipe = new gui_RelacaoPilotos(conn);
    }

    /**
     * metodo instancia tela RelacaoEquipe
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws exception_RegistroJaExisteException
     */
    public void criaRelacaoEquipe() throws ClassNotFoundException, SQLException, exception_RegistroJaExisteException{
        gui_RelacaoEquipes relacaoEquipe = new gui_RelacaoEquipes(conn);
    }

    /**
     * metodo instancia tela RelacaoProvas
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws exception_RegistroJaExisteException
     */
    public void criaRelacaoProvas() throws ClassNotFoundException, SQLException, exception_RegistroJaExisteException{
        gui_RelacaoProvas  relacaoProvas = new gui_RelacaoProvas(conn);
    }

    /**
     * instancia tela grafico provas
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws exception_RegistroJaExisteException
     */
    public void criaGraficoProvas() throws ClassNotFoundException, SQLException, exception_RegistroJaExisteException{
        gui_GraficoPiloto graficoPiloto = new gui_GraficoPiloto(conn);
    }

    /**
     * Instancia tela GraficoEquipe
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws exception_RegistroJaExisteException
     */
    public void criaGraficoEquipe() throws ClassNotFoundException, SQLException, exception_RegistroJaExisteException{
        gui_GraficoEquipe graficoEquipe = new gui_GraficoEquipe(conn);
    }

    /**
     * instancia tela graficoEvPiloto
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws exception_RegistroJaExisteException
     * @throws Exception
     */
    public void criaGraficoEvPiloto() throws ClassNotFoundException, SQLException, exception_RegistroJaExisteException, Exception{
        gui_GraficoEvPiloto graficoEvPiloto = new gui_GraficoEvPiloto(conn);
    }

    /**
     * Cria graficos de evolucao de equipes
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws exception_RegistroJaExisteException
     * @throws Exception
     */
    public void criaGraficoEvEquipe() throws ClassNotFoundException, SQLException, exception_RegistroJaExisteException, Exception{
        gui_GraficoEvEquipe graficoEquipe = new gui_GraficoEvEquipe(conn);
    }
    /**
     * Action performed para o menu pais
     *
     * @param evt
     */
    public void itemPaisActionPerformed(ActionEvent evt) throws exception_RegistroJaExisteException {
        criaPais();
    }

    /**
     * Action performed para o menu pista
     *
     * @param evt
     */
    public void itemPistaActionPerformed(ActionEvent evt) throws ClassNotFoundException, SQLException{
        criaPista();
    }

    /**
     * Action performed para menu escuderia (equipe)
     *
     * @param evt
     */
    public void itemEscuderiaActionPerformed(ActionEvent evt) throws ClassNotFoundException, SQLException{
        criaEscuderia();
    }

    /**
     * Action performed para menu piloto
     *
     * @param evt
     */
    public void itemPilotoActionPerformed(ActionEvent evt) throws ClassNotFoundException, SQLException{
        criaPiloto();
    }

    /**
     * Action performed para menu provas
     * @param evt
     */
    public void itemProvasActionPerformed(ActionEvent evt) throws ClassNotFoundException, SQLException{
        criaProvas();
    }

    /**
     * Action performed para menu gui_GridChegada
     *
     * @param evt
     */
    public void itemGridChegadaActionPerformed(ActionEvent evt) throws ClassNotFoundException, SQLException{
        criaGridChegada();
    }

    /**
     * Action performed para grid largada
     *
     * @param evt
     */
    public void itemGridLargadaActionPerformed(ActionEvent evt) throws ClassNotFoundException, SQLException{
        criaGridLargada();
    }

    /**
     * Action Performed para prova piloto
     * @param evt
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void itemProvaPilotoActionPerformed(ActionEvent evt) throws ClassNotFoundException, SQLException{
        criaPilotoProva();
    }

    /**
     * Action Event Resultado Largada
     * @param evt
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void itemResultadoLargada(ActionEvent evt) throws ClassNotFoundException, SQLException{
        criaResultLargada();
    }

    /**
     * action event resultado chegada
     * @param evt
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void itemResultadoChegada(ActionEvent evt) throws ClassNotFoundException, SQLException{
        criaResultChegada();
    }

    /**
     * action performed pontos piloto
     * @param evt
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws exception_RegistroJaExisteException
     */
    public void itemPontosPiloto(ActionEvent evt) throws ClassNotFoundException, SQLException, exception_RegistroJaExisteException{
        criaPontosPiloto();
    }

    /**
     * action performed Pontos equipe
     * @param evt
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws exception_RegistroJaExisteException
     */
    public void itemPontosEquipe(ActionEvent evt) throws ClassNotFoundException, SQLException, exception_RegistroJaExisteException{
        criaPontosEquipe();
    }

    /**
     * Action Performed Relacao Piloto
     * @param evt
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws exception_RegistroJaExisteException
     */
    public void itemRelacaoPiloto(ActionEvent evt) throws ClassNotFoundException, SQLException, exception_RegistroJaExisteException{
        criaRelacaoPiloto();
    }

    /**
     * Action performed Relacao Equipe
     * @param evt
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws exception_RegistroJaExisteException
     */
    public void itemRelacaoEquipe(ActionEvent evt) throws ClassNotFoundException, SQLException, exception_RegistroJaExisteException{
        criaRelacaoEquipe();
    }

    /**
     * action performed relacao provas
     * @param evt
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws exception_RegistroJaExisteException
     */
    public void itemRelacaoProvas(ActionEvent evt) throws ClassNotFoundException, SQLException, exception_RegistroJaExisteException{
        criaRelacaoProvas();
    }

    /**
     * Action Performed  Grafico Piloto
     * @param evt
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws exception_RegistroJaExisteException
     */
    public void itemGraficoPiloto(ActionEvent evt) throws ClassNotFoundException, SQLException, exception_RegistroJaExisteException{
        criaGraficoProvas();
    }

    /**
     * Action Performed Grafico Equipe
     * @param evt
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws exception_RegistroJaExisteException
     */
    public void itemGraficoEquipe(ActionEvent evt) throws ClassNotFoundException, SQLException, exception_RegistroJaExisteException{
        criaGraficoEquipe();
    }

    /**
     * Action performed Grafico Ev Piloto
     * @param evt
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws exception_RegistroJaExisteException
     * @throws Exception
     */
    public void itemGraficoEvPiloto(ActionEvent evt) throws ClassNotFoundException, SQLException, exception_RegistroJaExisteException, Exception{
        criaGraficoEvPiloto();
    }

    /**
     * Action Event da classe de Grafico de Evolucao de Equipe
     * @param evt
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws exception_RegistroJaExisteException
     * @throws Exception
     */
    public void itemGraficoEvEquipe(ActionEvent evt) throws ClassNotFoundException, SQLException, exception_RegistroJaExisteException, Exception{
        criaGraficoEvEquipe();
    }
}