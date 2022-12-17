package com.jorgediaz.emeals.datasources.remote;

import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;

@JsonClass(generateAdapter = true, generator = "java")
public class NutritionalInformationRemote {
    @Json(name = "focus")
    private Boolean focus;
    @Json(name = "name")
    private String name;
    @Json(name = "name_without_unit")
    private String nameWithoutUnit;
    @Json(name = "order")
    private int order;
    @Json(name = "should_combine")
    private Boolean shouldCombine;
    @Json(name = "unit")
    private String unit;
    @Json(name = "privateue")
    private Double privateue;

    public Boolean getFocus() {
        return focus;
    }

    public void setFocus(Boolean focus) {
        this.focus = focus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameWithoutUnit() {
        return nameWithoutUnit;
    }

    public void setNameWithoutUnit(String nameWithoutUnit) {
        this.nameWithoutUnit = nameWithoutUnit;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Boolean getShouldCombine() {
        return shouldCombine;
    }

    public void setShouldCombine(Boolean shouldCombine) {
        this.shouldCombine = shouldCombine;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getPrivateue() {
        return privateue;
    }

    public void setPrivateue(Double privateue) {
        this.privateue = privateue;
    }
}
