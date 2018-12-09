import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;

import javax.swing.*;

public class GameLog {

    private double[] scoreHistoryFirst;

    private double[] scoreHistorySecond;

    public double[] getScoreHistoryFirst() {
        return scoreHistoryFirst;
    }

    public void setScoreHistoryFirst(double[] scoreHistoryFirst) {
        this.scoreHistoryFirst = scoreHistoryFirst;
    }

    public double[] getScoreHistorySecond() {
        return scoreHistorySecond;
    }

    public void setScoreHistorySecond(double[] scoreHistorySecond) {
        this.scoreHistorySecond = scoreHistorySecond;
    }

    GameLog(int gamesAmount){
        scoreHistoryFirst = new double[gamesAmount];
        scoreHistorySecond = new double[gamesAmount];
    }

    GameLog(double[] sHistFirst, double[] sHistSecond){
        this.scoreHistoryFirst = sHistFirst;
        this.scoreHistorySecond = sHistSecond;
    }

    public void plotScoreHistories(){
        double[] x = new double[scoreHistoryFirst.length];
        for(int i = 0; i < x.length; i++){
            x[i] = i;
        }

        XYChart chart = new XYChartBuilder().width(400).height(400).xAxisTitle("Number of game").yAxisTitle("Money amount").title("Player money dynamic").build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideE);
        chart.addSeries("First player", x, scoreHistoryFirst).setMarker(SeriesMarkers.NONE);
        chart.addSeries("Second player", x, scoreHistorySecond).setMarker(SeriesMarkers.NONE);
        new SwingWrapper(chart).displayChart().setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    }


}
