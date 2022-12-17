package com.jorgediaz.emeals.datasources.remote;

import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;

import java.util.List;

@JsonClass(generateAdapter = true, generator = "java")
public class ItemRemote {

    @Json(name = "alternate_store")
    private Boolean alternateStore;
    @Json(name = "category")
    private String category;
    @Json(name = "id")
    private int id;
    @Json(name = "meal_numbers")
    private String mealNumbers;
    @Json(name = "name")
    private String name;
    @Json(name = "parsed_name")
    private String parsedName;
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
    @Json(name = "store_brand_name")
    private String storeBrandName;
    @Json(name = "sub_items")
    private List<SubItemRemote> subItems;
    @Json(name = "units")
    private String units;
    @Json(name = "units_friendly")
    private String unitsFriendly;
    @Json(name = "units_plural")
    private String unitsPlural;

    public Boolean getAlternateStore() {
        return alternateStore;
    }

    public void setAlternateStore(Boolean alternateStore) {
        this.alternateStore = alternateStore;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMealNumbers() {
        return mealNumbers;
    }

    public void setMealNumbers(String mealNumbers) {
        this.mealNumbers = mealNumbers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParsedName() {
        return parsedName;
    }

    public void setParsedName(String parsedName) {
        this.parsedName = parsedName;
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

    public String getStoreBrandName() {
        return storeBrandName;
    }

    public void setStoreBrandName(String storeBrandName) {
        this.storeBrandName = storeBrandName;
    }

    public List<SubItemRemote> getSubItems() {
        return subItems;
    }

    public void setSubItems(List<SubItemRemote> subItems) {
        this.subItems = subItems;
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
