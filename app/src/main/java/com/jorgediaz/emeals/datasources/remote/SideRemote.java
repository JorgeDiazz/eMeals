package com.jorgediaz.emeals.datasources.remote;

import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;

import java.util.List;

@JsonClass(generateAdapter = true, generator = "java")
public class SideRemote {
    @Json(name = "bucket")
    private String bucket;
    @Json(name = "calories")
    private int calories;
    @Json(name = "cook_time")
    private int cookTime;
    @Json(name = "ingredients")
    private String ingredients;
    @Json(name = "instructions")
    private String instructions;
    @Json(name = "notes")
    private String notes;
    @Json(name = "nutritional_information")
    private List<NutritionalInformationRemote> nutritionalInformation;
    @Json(name = "prep_time")
    private int prepTime;
    @Json(name = "rating")
    private Double rating;
    @Json(name = "servings")
    private int servings;
    @Json(name = "style")
    private String style;
    @Json(name = "style_id")
    private int styleId;
    @Json(name = "title")
    private String title;

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getCookTime() {
        return cookTime;
    }

    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<NutritionalInformationRemote> getNutritionalInformation() {
        return nutritionalInformation;
    }

    public void setNutritionalInformation(List<NutritionalInformationRemote> nutritionalInformation) {
        this.nutritionalInformation = nutritionalInformation;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public int getStyleId() {
        return styleId;
    }

    public void setStyleId(int styleId) {
        this.styleId = styleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
