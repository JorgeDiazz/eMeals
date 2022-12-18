package com.jorgediaz.domain;

import java.util.List;

public class Recipe {
    private int id;
    private String primaryPictureUrl;
    private String primaryPictureUrlMedium;
    private Double rating;
    private int time;
    private int servings;
    private String style;
    private String title;
    private List<NutritionalInformation> nutritionalInformationList;
    private List<String> ingredients;
    private List<String> instructions;
    private SideRecipe sideRecipe;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<NutritionalInformation> getNutritionalInformationList() {
        return nutritionalInformationList;
    }

    public void setNutritionalInformationList(List<NutritionalInformation> nutritionalInformationList) {
        this.nutritionalInformationList = nutritionalInformationList;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    public SideRecipe getSideRecipe() {
        return sideRecipe;
    }

    public void setSideRecipe(SideRecipe sideRecipe) {
        this.sideRecipe = sideRecipe;
    }
}