package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Raisin> raisins = readRaisinExcel("dataset/Raisin_Dataset.xlsx");
        Map<String, List<Raisin>> sample = toDivideSample(raisins, 0.8);
        List<Raisin> train = sample.get("train");
        List<Raisin> test = sample.get("test");
        
        int epochs = 1000;

        Perceptron perceptron = new Perceptron(7, 0.1);
        List<double[]> trainSamples = new ArrayList<>();
        List<Integer> trainLabels = new ArrayList<>();
        for (Raisin raisin : train) {
            double[] label = raisin.toSampleRow();
            trainSamples.add(label);
            trainLabels.add(raisin.toLabel());
        }

        // 퍼셉트론 학습
        perceptron.train(trainSamples, trainLabels, epochs);

        // 가중치와 편향 출력
        perceptron.printWeightsAndBias();

        // 예측 예시
        int testCount = 0;
        for (Raisin raisin : test) {
            double[] label = raisin.toSampleRow();
            int prediction = perceptron.predict(label);
            System.out.println(testCount + " Prediction for new input: " + prediction + " answer : " + raisin.toLabel());
            testCount += 1;
        }
    }
    public static Map<String, List<Raisin>> toDivideSample(List<Raisin> raisins, double trainRate){
        int kecimen = 0;
        int besni = 0;
        for (int i = 0; i < raisins.size(); i++) {
            Raisin raisin = raisins.get(i);
            if (raisin.getClassName().equals("Kecimen")){
                kecimen += 1;
            }else{
                besni += 1;
            }
        }

        List<Raisin> trainSamples = new ArrayList<>();
        List<Raisin> testSamples = new ArrayList<>();
        double sampleKecimenSize = kecimen * trainRate;
        double sampleBesniSize = besni * trainRate;
        for (int i = 0; i < raisins.size(); i++) {
            Raisin raisin = raisins.get(i);
            String className = raisin.getClassName();

            if (className.equals("Kecimen")) {
                if (sampleKecimenSize < 1.0) {
                    testSamples.add(raisin);
                } else {
                    trainSamples.add(raisin);
                    sampleKecimenSize -= 1;
                }
            } else {
                if (sampleBesniSize < 1.0) {
                    testSamples.add(raisin);
                } else {
                    trainSamples.add(raisin);
                    sampleBesniSize -= 1;
                }
            }
        }
        Map<String, List<Raisin>> result = new HashMap<>();
        result.put("train", trainSamples);
        result.put("test", testSamples);
        return result;
    }

    public static InputStream reloadFile(String path){
        ClassLoader classLoader = ResourceLoader.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(path);
        if (inputStream == null) {
            System.out.println("파일을 찾을 수 없습니다.");
        } else {
            System.out.println("파일을 성공적으로 불러왔습니다.");
        }
        return inputStream;
    }

    public static List<Raisin> readRaisinExcel(String path){
        List<Raisin> raisins = new ArrayList<>();
        try (InputStream fis = reloadFile(path); Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Raisin raisin = new Raisin();
                if (row.getRowNum() > 0){
                    raisin.setArea(row.getCell(0).getNumericCellValue());
                    raisin.setMajorAxisLength(row.getCell(1).getNumericCellValue());
                    raisin.setMinorAxisLength(row.getCell(2).getNumericCellValue());
                    raisin.setEccentricity(row.getCell(3).getNumericCellValue());
                    raisin.setConvexArea(row.getCell(4).getNumericCellValue());
                    raisin.setExtent(row.getCell(5).getNumericCellValue());
                    raisin.setPerimeter(row.getCell(6).getNumericCellValue());
                    raisin.setClassName(row.getCell(7).getStringCellValue());
                    raisins.add(raisin);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return raisins;
    }

}