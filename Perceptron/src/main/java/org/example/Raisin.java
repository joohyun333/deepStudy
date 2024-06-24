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


}
