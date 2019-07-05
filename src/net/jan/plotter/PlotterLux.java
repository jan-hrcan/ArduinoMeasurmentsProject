package net.jan.plotter;

import javafx.application.Platform;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;

public class PlotterLux {

    private LineChart<Number, Number> lineChartLux;
    private XYChart.Series<Number, Number> seriesLux;
    private static double time = 0.0;
    private Axis xAxisLux;
    private NumberAxis yAxisLux;

    public PlotterLux(HBox root){
        // lux
        xAxisLux = new NumberAxis();
        xAxisLux.setLabel("Time");
        yAxisLux = new NumberAxis(0,1200,100);
        yAxisLux.setLabel("Lux");

        lineChartLux = new LineChart<Number, Number>(xAxisLux, yAxisLux);

        seriesLux = new XYChart.Series<Number, Number>();

        lineChartLux.getData().add(seriesLux);

        root.getChildren().add(lineChartLux);
    }

    public void updatePlotLux(double num) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                seriesLux.getData().add(new XYChart.Data<>(time+=1, num));
            }
        });
    }
}
