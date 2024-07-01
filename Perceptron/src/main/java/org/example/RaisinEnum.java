package org.example;

public enum RaisinEnum {
    area("면적"),
    majorAxisLength("장축길이"),
    minorAxisLength("단축길이"),
    eccentricity("편심률"),
    convexArea("볼록 면적"),
    extent("범위"),
    perimeter("둘레"),
    className("분류명")
    ;

    private String krName;
    RaisinEnum(String krName) {
        this.krName = krName;
    }
    public String getKrName(){
        return krName;
    }
}
