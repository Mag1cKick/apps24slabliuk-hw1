package ua.edu.ucu.apps.tempseries;

import java.util.InputMismatchException;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class TemperatureSeriesAnalysis {
    private double[] temperatureSeries;

    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[1];
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        this.temperatureSeries = temperatureSeries;
    }

    public double sum(){
        double res = 0;
        for (int i = 0; i < temperatureSeries.length; i++)
        {
            res += temperatureSeries[i];
        }
        return res;
    }
    public double average() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("No pizza here");
        }
        return this.sum() / temperatureSeries.length;
    }

    public double deviation() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("No pizza here");
        }
        double mean = average();
        double res_sum = 0;

        for (int i = 0; i < temperatureSeries.length; i++) {
            double dif = temperatureSeries[i] - mean;
            res_sum += dif * dif;
        }
        return Math.sqrt(res_sum/temperatureSeries.length);
    }

    public double min() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("No pizza here");
        }
        double res = temperatureSeries[0];
        for (int i = 0; i < temperatureSeries.length; i++){
            if (res > temperatureSeries[i]){
                res = temperatureSeries[i];
            }
        }
        return res;
    }

    public double max() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("No pizza here");
        }
        double res = temperatureSeries[0];
        for (int i = 0; i < temperatureSeries.length; i++){
            if (res < temperatureSeries[i]){
                res = temperatureSeries[i];
            }
        }
        return res;
    }

    public double findTempClosestToZero() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("No pizza here");
        }
        double res = temperatureSeries[0];
        for (int i = 0; i < temperatureSeries.length; i++){
            if (Math.abs(res) == Math.abs(temperatureSeries[i])){
                res = Math.abs(res);
            }
            if (Math.abs(res) > Math.abs(temperatureSeries[i])){
                res = temperatureSeries[i];
            }
        }
        return res;
    }

    public double findTempClosestToValue(double tempValue) {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("No pizza here");
        }
        double res = temperatureSeries[0];
        for (int i = 0; i < temperatureSeries.length; i++){
            if (Math.abs(res - tempValue) == Math.abs(temperatureSeries[i] - tempValue)){
                res = Math.abs(res);
            }
            if (Math.abs(res - tempValue) > Math.abs(temperatureSeries[i] - tempValue)){
                res = temperatureSeries[i];
            }
        }
        return res;
    }

    public double[] findTempsLessThen(double tempValue) {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("No pizza here");
        }
        int count = 0;
        for (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] < tempValue) {
                count++;
            }
        }
        double[] res = new double[count];
        count = 0;
        for (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] < tempValue) {
                res[count] = temperatureSeries[i];
                count++;
            }
        }
        return res;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("No pizza here");
        }
        int count = 0;
        for (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] > tempValue) {
                count++;
            }
        }
        double[] res = new double[count];
        count = 0;
        for (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] > tempValue) {
                res[count] = temperatureSeries[i];
                count++;
            }
        }
        return res;
    }

    public double[] findTempsInRange(double lowerBound, double upperBound) {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("No pizza here");
        }
        int count = 0;
        for (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] > lowerBound &&
            temperatureSeries[i] < upperBound) {
                count++;
            }
        }
        double[] res = new double[count];
        count = 0;
        for (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] > lowerBound &&
            temperatureSeries[i] < upperBound) {
                res[count] = temperatureSeries[i];
                count++;
            }
        }
        return res;
    }

    public void reset() {
        temperatureSeries = new double[temperatureSeries.length];

    }

    public double[] sortTemps() {
        boolean a = true;
        while (a){
            a = false;
            for (int i = 0; i < temperatureSeries.length - 1; i++){
                if (temperatureSeries[i] > temperatureSeries[i+1]){
                    double save = temperatureSeries[i];
                    temperatureSeries[i] = temperatureSeries[i+1];
                    temperatureSeries[i+1] = save;
                    a = true;
                }
            }
            
        }
        return temperatureSeries;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("No pizza here");
        }
        return new TempSummaryStatistics(this.average(), this.deviation(), this.min(), this.max());
    }

    public int addTemps(double... temps) {
        if (temps.length == 0) {
            return temperatureSeries.length;
        }

        int len = temperatureSeries.length;

        for (int i = 0; i < temps.length; i++) {
            if (temps[i] < -273) {
                throw new InputMismatchException("Pizza is too cold");
            }
        }

        if (temperatureSeries.length + temps.length > temperatureSeries.length) {
            double[] newTemperatureSeries = new double[Math.max(temperatureSeries.length * 2, temperatureSeries.length + temps.length)];
            for (int i = 0; i < temperatureSeries.length; i++){
                newTemperatureSeries[i] = temperatureSeries[i];
            };
            temperatureSeries = newTemperatureSeries;
        }

        for (int i = 0; i < temps.length; i++) {
            temperatureSeries[len + i] = temps[i];
        }

        return temperatureSeries.length;
    }
}
