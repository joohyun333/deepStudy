package org.example.mains;

import java.util.*;

public class CovarianceMainTest {
    public static void main(String[] args) {
        List<Double[]> train = new ArrayList<>();
        train.add(new Double[]{170.0,60.0,4.1});
        train.add(new Double[]{165.0,55.0,3.0});
        train.add(new Double[]{174.0,75.0,2.8});
        train.add(new Double[]{169.0,67.0,2.9});
        train.add(new Double[]{155.0,49.0,3.1});
        train.add(new Double[]{172.0,63.0,3.6});
        train.add(new Double[]{166.0,58.0,3.7});
        train.add(new Double[]{168.0,61.0,4.0});
        int characterCount = 3;
        double[] averages = new double[characterCount];
        for (int i = 0; i < train.size(); i++) {
            Double[] raisin = train.get(i);
            averages[0] += raisin[0];
            averages[1] += raisin[1];
            averages[2] += raisin[2];
        }
        for (int i = 0; i < characterCount; i++) {
            double v = averages[i] / train.size();
            averages[i] = v;
        }

        double[][] covariance = new double[characterCount][characterCount];
        for (int i = 0; i < train.size(); i++) {
            Double[] sampleTotalRow = train.get(i);
            double[] difference = new double[characterCount];
            for (int j = 0; j < characterCount; j++) {
                difference[j] =  averages[j] - sampleTotalRow[j];
            }

            for (int j = 0; j < characterCount; j++) {
                for (int k = 0; k < characterCount; k++) {
                    covariance[j][k] += difference[j] * difference[k];
                }
            }
        }
        for (int j = 0; j < characterCount; j++) {
            for (int k = 0; k < characterCount; k++) {
                covariance[j][k] = Double.parseDouble(String.format("%.3f", covariance[j][k] / (train.size() - 1)));
            }
        }

        for (int j = 0; j < characterCount; j++) {
            System.out.println(Arrays.toString(covariance[j]));
        }
    }
}