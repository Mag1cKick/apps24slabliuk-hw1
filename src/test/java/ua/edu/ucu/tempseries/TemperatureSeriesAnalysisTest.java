package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;

import java.util.InputMismatchException;

import org.junit.Test;
import ua.edu.ucu.apps.tempseries.TemperatureSeriesAnalysis;
import ua.edu.ucu.apps.tempseries.TempSummaryStatistics;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void testSum() {
        double[] temperatureSeries = {10.0, 20.0, 30.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expectedResult = 60.0;

        double actualResult = seriesAnalysis.sum();
        assertEquals(expectedResult, actualResult, 0.00001);
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {10.0, 20.0, 30.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expectedResult = 20.0;

        double actualResult = seriesAnalysis.average();
        assertEquals(expectedResult, actualResult, 0.00001);
    }

    @Test
    public void testDeviation() {
        double[] temperatureSeries = {10.0, 20.0, 30.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expectedResult = Math.sqrt(200.0 / 3); // Standard deviation calculation

        double actualResult = seriesAnalysis.deviation();
        assertEquals(expectedResult, actualResult, 0.00001);
    }

    @Test
    public void testMin() {
        double[] temperatureSeries = {10.0, 20.0, -5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expectedResult = -5.0;

        double actualResult = seriesAnalysis.min();
        assertEquals(expectedResult, actualResult, 0.00001);
    }

    @Test
    public void testMax() {
        double[] temperatureSeries = {10.0, 20.0, -5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expectedResult = 20.0;

        double actualResult = seriesAnalysis.max();
        assertEquals(expectedResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToZero() {
        double[] temperatureSeries = {10.0, -5.0, 3.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expectedResult = 3.0;

        double actualResult = seriesAnalysis.findTempClosestToZero();
        assertEquals(expectedResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToValue() {
        double[] temperatureSeries = {10.0, 20.0, 30.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expectedResult = 20.0;

        double actualResult = seriesAnalysis.findTempClosestToValue(18.0);
        assertEquals(expectedResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsLessThen() {
        double[] temperatureSeries = {10.0, 20.0, 30.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expectedResult = {10.0};

        double[] actualResult = seriesAnalysis.findTempsLessThen(15.0);
        assertArrayEquals(expectedResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsGreaterThen() {
        double[] temperatureSeries = {10.0, 20.0, 30.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expectedResult = {20.0, 30.0};

        double[] actualResult = seriesAnalysis.findTempsGreaterThen(15.0);
        assertArrayEquals(expectedResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsInRange() {
        double[] temperatureSeries = {10.0, 20.0, 30.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expectedResult = {20.0};

        double[] actualResult = seriesAnalysis.findTempsInRange(15.0, 25.0);
        assertArrayEquals(expectedResult, actualResult, 0.00001);
    }

    @Test
    public void testReset() {
        double[] temperatureSeries = {10.0, 20.0, 30.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.reset();
        
        // After reset, the length should be 0 and should throw exception when calling min or max
        assertEquals(0, seriesAnalysis.findTempsLessThen(0).length);
    }

    @Test
    public void testSortTemps() {
        double[] temperatureSeries = {30.0, 10.0, 20.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expectedResult = {10.0, 20.0, 30.0};

        double[] actualResult = seriesAnalysis.sortTemps();
        assertArrayEquals(expectedResult, actualResult, 0.00001);
    }

    @Test
    public void testSummaryStatistics() {
        double[] temperatureSeries = {10.0, 20.0, 30.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        TempSummaryStatistics stats = seriesAnalysis.summaryStatistics();

        assertEquals(20.0, stats.getAvgTemp(), 0.00001);
        assertEquals(seriesAnalysis.deviation(), stats.getDevTemp(), 0.00001);
        assertEquals(10.0, stats.getMinTemp(), 0.00001);
        assertEquals(30.0, stats.getMaxTemp(), 0.00001);
    }

    @Test(expected = InputMismatchException.class)
    public void testAddTempsBelowAbsoluteZero() {
        double[] temperatureSeries = {10.0, 20.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        
        seriesAnalysis.addTemps(15.0, -300.0); // This should throw an exception
    }

    @Test
    public void testAddTemps() {
        double[] temperatureSeries = {10.0, 20.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        int newSize = seriesAnalysis.addTemps(15.0, 25.0);
        
        assertEquals(4, newSize);
        assertArrayEquals(new double[]{10.0, 20.0, 15.0, 25.0}, seriesAnalysis.getTemperatureSeries(), 0.00001);
    }
}
