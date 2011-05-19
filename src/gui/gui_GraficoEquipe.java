/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import dao.dao_GridChegada;
import dao.exception_RegistroJaExisteException;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JDialog;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


/**
 *
 * @author edipoterra
 */
public class gui_GraficoEquipe extends JDialog{
    private Connection conn;
    /**
     * metodo construtor de grafico de equipe
     * @throws exception_RegistroJaExisteException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public gui_GraficoEquipe() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException {
        montaComps();

        this.setTitle("Grafico Equipe");
        this.setBounds(100, 100, 400, 310);
        this.setResizable(false);
        this.setModal(true);
        this.setVisible(true);

    }

    /**
     * metodo construtor de grafico de equipe com conexao
     * @param conn
     * @throws exception_RegistroJaExisteException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public gui_GraficoEquipe(Connection conn) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException {
        this.conn = conn;
        montaComps();

        this.setTitle("Grafico Equipe");
        this.setBounds(100, 100, 400, 310);
        this.setResizable(false);
        this.setModal(true);
        this.setVisible(true);

    }

    /**
     * monta componentes do grafico
     * @throws exception_RegistroJaExisteException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void montaComps() throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException{
        dao_GridChegada chegada = new dao_GridChegada(conn);
        CategoryDataset dataset = gui_GraficoEquipe.createDataset(chegada);
        JFreeChart chart = gui_GraficoEquipe.createBarChart(dataset);
        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(400, 300));
        setContentPane(panel);
    }

    /**
     * Metodo de dataset para mandar para o grafico
     * @param chegada
     * @return
     * @throws exception_RegistroJaExisteException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private static CategoryDataset createDataset(dao_GridChegada chegada) throws exception_RegistroJaExisteException, ClassNotFoundException, SQLException {
        String[][] dados;
        dados = chegada.selectPontosEquipe();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < dados[0].length; i++){
            dataset.addValue(Integer.parseInt(dados[2][i]), dados[1][i], dados[1][i]);
        }

        return dataset;
    }

    /**
     * Metodo responsavel pelo grafico
     * @param dataset
     * @return
     */
    private static JFreeChart createBarChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
        "Pontuacao de Pilotos", //Titulo
        "Equipes", // Eixo X
        "Pontos", //Eixo Y
        dataset, // Dados para o grafico
        PlotOrientation.HORIZONTAL, //Orientacao do grafico
        true, false, false); // exibir: legendas, tooltips, url
        return chart;
    }

}