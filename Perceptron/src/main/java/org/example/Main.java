package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Raisin> raisins = readRaisinExcel("dataset/Raisin_Dataset.xlsx");

        System.out.println(raisins);
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