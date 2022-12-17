package com.jorgediaz.emeals.datasources.remote;

import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;

@JsonClass(generateAdapter = true, generator = "java")
public class SubItemRemote {

    @Json(name = "id")
    private int id;
    @Json(name = "meal_number")
    private int mealNumber;
    @Json(name = "name")
    private String name;
    @Json(name = "quantity")
    private Double quantity;
    @Json(name = "quantity_fraction")
    private String quantityFraction;
    @Json(name = "quantity_number")
    private int quantityNumber;
    @Json(name = "size")
    private String size;
    @Json(name = "size_units")
    private String sizeUnits;
    @Json(name = "units")
    private String units;
    @Json(name = "units_friendly")
    private String unitsFriendly;
    @Json(name = "units_plural")
    private String unitsPlural;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMealNumber() {
        return mealNumber;
    }

    public void setMealNumber(int mealNumber) {
        this.mealNumber = mealNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getQuantityFraction() {
        return quantityFraction;
    }

    public void setQuantityFraction(String quantityFraction) {
        this.quantityFraction = quantityFraction;
    }

    public int getQuantityNumber() {
        return quantityNumber;
    }

    public void setQuantityNumber(int quantityNumber) {
        this.quantityNumber = quantityNumber;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSizeUnits() {
        return sizeUnits;
    }

    public void setSizeUnits(String sizeUnits) {
        this.sizeUnits = sizeUnits;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getUnitsFriendly() {
        return unitsFriendly;
    }

    public void setUnitsFriendly(String unitsFriendly) {
        this.unitsFriendly = unitsFriendly;
    }

    public String getUnitsPlural() {
        return unitsPlural;
    }

    public void setUnitsPlural(String unitsPlural) {
        this.unitsPlural = unitsPlural;
    }
}
