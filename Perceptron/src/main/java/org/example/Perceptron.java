package org.example;

import java.util.Arrays;
import java.util.List;

public class Perceptron {

    private double[] weights;
    private double bias;
    private final double learningRate;
    private final int featureCount;
    public Perceptron(int featureCount, double learningRate) {
        this.featureCount = featureCount;
        this.bias = 0.0;
        this.learningRate = learningRate;
    }

    private int activation(double x) {
        return x >= 0 ? 1 : -1;
    }

    public int predict(double[] inputs) {
        double sum = bias;
        for (int i = 0; i < inputs.length; i++) {
            sum += weights[i] * inputs[i];
        }
        return activation(sum);
    }

    public double loss(double answer ,int prediction) {
        if (answer == (double) prediction){
            return 0;
        }else{
            return (-answer) * prediction;
        }
    }

    // 포켓 알고리즘 사용
    public void train(List<double[]> sample, List<Integer> labels, int epochs) {
        double[] copyWeights = new double[featureCount];
        for (int i = 0; i < featureCount; i++) {
            copyWeights[i] = 1;
        }
        double copyBias = bias;
        boolean stopCondition = false;
        while(!stopCondition){
            stopCondition = true;
            weights = copyWeights.clone();
            bias = copyBias;

            System.out.println(Arrays.toString(weights) + " bias : " + bias);

            // 가중치와 편향 업데이트
            for (int i = 0; i < sample.size(); i++) {
                int prediction = predict(sample.get(i));
                if ((double)prediction != labels.get(i)) {
                    stopCondition = false;
                    for (int j = 0; j < weights.length; j++) {
                        copyWeights[j] += learningRate * labels.get(i) * sample.get(i)[j];
                    }
                    copyBias += learningRate * labels.get(i);
                }
            }
            weights = copyWeights;
            bias = copyBias;
        }
//         for (int epoch = 0; epoch < epochs; epoch++) {
//             weights = copyWeights.clone();
//             bias = copyBias;
//             // 가중치와 편향 업데이트
//            for (int i = 0; i < sample.size(); i++) {
//                int prediction = predict(sample.get(i));
//                if ((double)prediction != labels.get(i)) {
//                    for (int j = 0; j < weights.length; j++) {
//                        copyWeights[j] += learningRate * labels.get(i) * sample.get(i)[j];
//                    }
//                    copyBias += learningRate * labels.get(i);
//                }
//            }
//        }
//        weights = copyWeights;
//        bias = copyBias;

    }

    public void printWeightsAndBias() {
        System.out.println("Weights: ");
        for (double weight : weights) {
            System.out.print(weight + " ");
        }
        System.out.println("\nBias: " + bias);
    }
}
