package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.ui.ApplicationFrame;

import java.awt.*;
import java.util.List;

public class ScatterPlot extends ApplicationFrame {


    public ScatterPlot(String title, List<Raisin> data, RaisinEnum param1, RaisinEnum param2) {
        super(title);
        JFreeChart scatterPlot = createChart(title, createDataset(data, param1, param2), param1, param2);
        ChartPanel chartPanel = new ChartPanel(scatterPlot);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 700));
        setContentPane(chartPanel);
    }

    private XYSeriesCollection createDataset(List<Raisin> data, RaisinEnum param1, RaisinEnum param2) {


        XYSeries kecimen = new XYSeries("kecimen");
        XYSeries besni = new XYSeries("Besni");

        for (int i = 0; i < data.size(); i++) {
            Raisin raisin = data.get(i);
            double param1Val = raisin.enumTORaisin(param1);
            double param2Val = raisin.enumTORaisin(param2);
            if(raisin.getClassName().equals("Kecimen")){
                kecimen.add(param1Val,param2Val);
            }else{
                besni.add(param1Val,param2Val);
            }
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(kecimen);
        dataset.addSeries(besni);

        return dataset;
    }
    private JFreeChart createChart(String title, XYSeriesCollection dataset, RaisinEnum param1, RaisinEnum param2) {
        JFreeChart chart = ChartFactory.createScatterPlot(
                title,
                param1.getKrName(),
                param2.getKrName(),
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        XYPlot plot = (XYPlot) chart.getPlot();
        XYItemRenderer renderer = plot.getRenderer();
        if (renderer instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer scatterRenderer = (XYLineAndShapeRenderer) renderer;
            scatterRenderer.setSeriesPaint(0, Color.BLUE);
            scatterRenderer.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-1, -1, 2, 2));
            scatterRenderer.setSeriesPaint(1, Color.red);
            scatterRenderer.setSeriesShape(1, new java.awt.geom.Ellipse2D.Double(-1, -1, 2, 2));
        }
        Font font = new Font("나눔고딕", Font.PLAIN, 12);
        chart.getTitle().setFont(font);
        plot.getDomainAxis().setLabelFont(font);
        plot.getDomainAxis().setTickLabelFont(font);
        plot.getRangeAxis().setLabelFont(font);
        plot.getRangeAxis().setTickLabelFont(font);
        chart.getLegend().setItemFont(font);

        return chart;
    }
}
