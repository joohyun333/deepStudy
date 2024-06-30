package org.example;

import java.util.List;

public class Perceptron {

    private double[] weights;
    private double bias;
    private double learningRate;
    public Perceptron(int featureCount, double learningRate) {
        double[] doubles = new double[featureCount];
        for (int i = 0; i < featureCount; i++) {
            doubles[i] = 1;
        }
        this.weights = doubles;
        this.bias = 0.0;
        this.learningRate = learningRate;
    }

    private int activation(double x) {
        return x >= 0 ? 1 : 0;
    }

    public int predict(double[] inputs) {
        double sum = bias;
        for (int i = 0; i < inputs.length; i++) {
            sum += weights[i] * inputs[i];
        }
        return activation(sum);
    }

    public void train(List<double[]> sample, List<Integer> labels, int epochs) {
         for (int epoch = 0; epoch < epochs; epoch++) {
            for (int i = 0; i < sample.size(); i++) {
                int prediction = predict(sample.get(i));
                int error = labels.get(i) - prediction;

                // 가중치와 편향 업데이트
                for (int j = 0; j < weights.length; j++) {
                    weights[j] += learningRate * error * sample.get(i)[j];
                }
                bias += learningRate * error;
            }
        }
    }

    public void printWeightsAndBias() {
        System.out.println("Weights: ");
        for (double weight : weights) {
            System.out.print(weight + " ");
        }
        System.out.println("\nBias: " + bias);
    }
}
