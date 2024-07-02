package org.example;

import lombok.Data;

@Data
public class Raisin {
    public double area; // 면적
    public double majorAxisLength; // 장축길이
    public double minorAxisLength; // 단축길이
    public double eccentricity; //편심률(0에 가까울수록 원형에 가깝고, 1에 가까울수록 타원)
    public double convexArea; // 볼록 면적
    public double extent; //범위
    public double perimeter; // 둘레
    public String className; // 분류명

    public double[] toSampleTotalRow(){
        return new double[]{area, majorAxisLength, minorAxisLength, eccentricity, convexArea, extent, perimeter};
    }
    public double[] toSampleRow(){
        return new double[]{area, convexArea};
    }

    public double enumTORaisin(RaisinEnum raisinEnum){
        String krName = raisinEnum.getKrName();
        if(krName.equals("면적")){
            return area;
        } else if (krName.equals("장축길이")) {
            return majorAxisLength;
        }else if (krName.equals("단축길이")) {
            return minorAxisLength;
        }else if (krName.equals("편심률")) {
            return eccentricity;
        }else if (krName.equals("볼록 면적")) {
            return convexArea;
        }else if (krName.equals("범위")) {
            return extent;
        }else {
            return perimeter;
        }
    }

    public static String idxToColName(int idx){
        if(idx == 0) { return "면적"; }
        else if(idx == 1) { return "장축길이"; }
        else if(idx == 2) { return "단축길이"; }
        else if(idx == 3) { return "편심률"; }
        else if(idx == 4) { return "볼록 면적"; }
        else if(idx == 5) { return "범위"; }
        else { return "둘레"; }
    }

    public int toLabel(){
        if(className.equals("Kecimen")){
            return -1;
        }else {
            return 1;
        }
    }
}
