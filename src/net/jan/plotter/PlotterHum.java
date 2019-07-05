package net.jan.plotter;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Created by ElFuego on 5/21/2017.
 */
public class PlotterHum {

    private LineChart<Number, Number> lineChartHumidity;
    private XYChart.Series<Number, Number> seriesHum;
    private static double time = 0.0;
    private NumberAxis xAxisHum;
    private NumberAxis yAxisHum;

    public PlotterHum(HBox root){
        // humidity
        xAxisHum = new NumberAxis();
        xAxisHum.setLabel("Time");
        yAxisHum = new NumberAxis(0, 100, 10);
        yAxisHum.setLabel("%");

        lineChartHumidity = new LineChart<Number, Number>(xAxisHum, yAxisHum);

        seriesHum = new XYChart.Series<Number, Number>();

        lineChartHumidity.getData().add(seriesHum);

        root.getChildren().add(lineChartHumidity);
    }

    public void updatePlotHum(double num) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                seriesHum.getData().add(new XYChart.Data<>(time+=2, num));
            }
        });
    }
}
