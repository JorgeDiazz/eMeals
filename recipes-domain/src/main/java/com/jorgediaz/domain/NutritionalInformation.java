package com.jorgediaz.domain;

public class NutritionalInformation {

    private final String name;
    private final double value;
    private final String unit;

    public NutritionalInformation(String name, double value, String unit) {
        this.name = name;
        this.value = value;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }
}
