package statistcs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Statistics {

    private List<Double> responseTimes;

    public Statistics() {
        this.responseTimes = new ArrayList<>();
    }

    public void addResponseTime(double time) {
        responseTimes.add(time);
        Collections.sort(responseTimes);
    }

    public double calculateMean() {
        int start = (int) (0.05 * responseTimes.size());
        int end = (int) (0.95 * responseTimes.size());
        double sum = 0;
        for (int i = start; i <= end; i++) {
            sum += responseTimes.get(i);
        }
        return sum / (end - start + 1);
    }

    public double calculateMedian() {
        int size = responseTimes.size();
        if (size % 2 == 0) {
            return (responseTimes.get(size / 2 - 1) + responseTimes.get(size / 2)) / 2.0;
        } else {
            return responseTimes.get(size / 2);
        }
    }

    public double calculateStandardDeviation() {
        int start = (int) (0.05 * responseTimes.size());
        int end = (int) (0.95 * responseTimes.size());
        double mean = calculateMean();
        double sum = 0;
        for (int i = start; i <= end; i++) {
            sum += Math.pow(responseTimes.get(i) - mean, 2);
        }
        double variance = sum / (end - start );
        return Math.sqrt(variance);
    }
}
