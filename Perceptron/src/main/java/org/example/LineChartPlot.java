package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYLineAnnotation;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.util.List;

public class LineChartPlot extends ApplicationFrame {


    public LineChartPlot(String title, List<Raisin> data, RaisinEnum param1, RaisinEnum param2, double xSlope, double bias) {
        super(title);
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

        // 차트 생성
        JFreeChart scatterPlot = ChartFactory.createScatterPlot(
                title,
                param1.getKrName(),
                param2.getKrName(),
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        XYPlot plot = scatterPlot.getXYPlot();
        double x1 = 20000;
        double y1 = xSlope * x1 + bias;
        System.out.println(y1);
        double x2 = 280000;
        double y2 = xSlope * x2 + bias;
        System.out.println(y2);
        XYLineAnnotation annotation = new XYLineAnnotation(x1, y1, x2, y2, new BasicStroke(2.0f), Color.black);
        plot.addAnnotation(annotation);

        ChartPanel chartPanel = new ChartPanel(scatterPlot);
        chartPanel.setPreferredSize(new java.awt.Dimension(1000, 1000));
        setContentPane(chartPanel);
    }
}
