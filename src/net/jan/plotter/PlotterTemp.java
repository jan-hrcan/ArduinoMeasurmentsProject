package net.jan.plotter;

import javafx.application.Platform;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;

import static jdk.nashorn.internal.runtime.regexp.joni.ast.Node.*;

public class PlotterTemp{

    private LineChart<Number, Number> lineChartTemperature;
    private XYChart.Series<Number, Number> seriesTemp;
    private static double time = 0.0;
    private NumberAxis xAxisTemp;
    private NumberAxis yAxisTemp;

    public PlotterTemp(HBox root) {
        // temperature
        xAxisTemp = new NumberAxis();
        xAxisTemp.setLabel("Time");
        yAxisTemp = new NumberAxis(0, 50, 5);
        yAxisTemp.setLabel("C°");


        lineChartTemperature = new LineChart<Number, Number>(xAxisTemp, yAxisTemp);
        lineChartTemperature.applyCss();

        seriesTemp = new XYChart.Series<Number, Number>();

        lineChartTemperature.getData().add(seriesTemp);

        root.getChildren().add(lineChartTemperature);
    }


    public void updatePlotTemp(double num) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                seriesTemp.getData().add(new XYChart.Data<>(time+=2, num));
            }
        });
    }
}
