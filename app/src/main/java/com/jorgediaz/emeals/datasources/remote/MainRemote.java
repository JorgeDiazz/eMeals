package com.jorgediaz.emeals.datasources.remote;

import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;

import java.util.List;
import java.util.Map;

@JsonClass(generateAdapter = true, generator = "java")
public class MainRemote {

    @Json(name = "bucket")
    private String bucket;
    @Json(name = "calories")
    private int calories;
    @Json(name = "comment")
    private String comment;
    @Json(name = "cook_time")
    private int cookTime;
    @Json(name = "image")
    private String image;
    @Json(name = "ingredients")
    private Map<String, String> ingredients;
    @Json(name = "instructions")
    private Map<String, String> instructions;
    @Json(name = "notes")
    private String notes;
    @Json(name = "nutritional_information")
    private List<NutritionalInformationRemote> nutritionalInformation;
    @Json(name = "prep_time")
    private int prepTime;
    @Json(name = "primary_picture_path")
    private String primaryPicturePath;
    @Json(name = "primary_picture_url")
    private String primaryPictureUrl;
    @Json(name = "primary_picture_url_medium")
    private String primaryPictureUrlMedium;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getCookTime() {
        return cookTime;
    }

    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Map<String, String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Map<String, String> ingredients) {
        this.ingredients = ingredients;
    }

    public Map<String, String> getInstructions() {
        return instructions;
    }

    public void setInstructions(Map<String, String> instructions) {
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

    public String getPrimaryPicturePath() {
        return primaryPicturePath;
    }

    public void setPrimaryPicturePath(String primaryPicturePath) {
        this.primaryPicturePath = primaryPicturePath;
    }

    public String getPrimaryPictureUrl() {
        return primaryPictureUrl;
    }

    public void setPrimaryPictureUrl(String primaryPictureUrl) {
        this.primaryPictureUrl = primaryPictureUrl;
    }

    public String getPrimaryPictureUrlMedium() {
        return primaryPictureUrlMedium;
    }

    public void setPrimaryPictureUrlMedium(String primaryPictureUrlMedium) {
        this.primaryPictureUrlMedium = primaryPictureUrlMedium;
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
