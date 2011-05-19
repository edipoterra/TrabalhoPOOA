package gui;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author heliokann
 */

//import agente.papel.Papel;
//import framework.agentRole.AgentRole;
import dao.dao_GridChegada;
import java.awt.Color;
import java.sql.Connection;

import javax.swing.JDialog;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
//import util.Arquivo;
//import util.GeradorRelatorio;

/**
 * A simple demonstration of the crosshairs that can be displayed in an {@link XYPlot}.
 *
 * @author David Gilbert
 */
public class gui_GraficoEvPiloto extends JDialog {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */

//    private Arquivo arquivo = null;
    public Connection conn;

    /**
     * construtor da classe graficosEvPiloto
     * @param conn
     * @throws Exception
     */
    public gui_GraficoEvPiloto(Connection conn) throws Exception{
        this.conn = conn;
        XYDataset dataset = createDataset();
        JFreeChart chart = createChart("Evolucao Pilotos", dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
        this.setTitle("Consulta Pontos do Piloto");
        this.setBounds(100, 100, 400, 310);
        this.setResizable(false);
        this.setModal(true);
        this.setVisible(true);

    }

    /**
     * Dataset para o grafico XY
     *
     * @return a sample dataset.
     */
    private XYDataset createDataset() throws Exception{
        String[][] dados;

        dao_GridChegada chegada = new dao_GridChegada(conn);
        dados = chegada.selectEvoPiloto();

        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series = new XYSeries("");
        String aux = "";

        for (int i = 0; i < dados[0].length; i++){
            if (dados[0][i].equals(aux)){
                series.add(i, Integer.parseInt(dados[2][i]));
            }
            else{
                if (i > 0){
                    dataset.addSeries(series);
                }

                series = new XYSeries(dados[0][i]);
                series.add(i, Integer.parseInt(dados[2][i]));
                aux = dados[0][i];
            }
        }
        return dataset;
    }

    /**
     * Cria o grafico em si
     *
     * @param dataset  the data for the chart.
     *
     * @return a chart.
     */
    private JFreeChart createChart(String titulo, XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
            titulo,       // chart title
            "Pontuacao",                      // x axis label
            "Porcentagem",                      // y axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );

        chart.setBackgroundPaint(Color.white);

        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        plot.setDomainCrosshairVisible(true);
        plot.setDomainCrosshairLockedOnData(true);
        plot.setRangeCrosshairVisible(true);
        plot.setRangeCrosshairLockedOnData(true);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        return chart;

    }
}

