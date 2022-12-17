package com.jorgediaz.domain;

import java.util.List;

public class SideRecipe {

    private int servings;
    private String style;
    private String title;
    private List<NutritionalInformation> nutritionalInformationList;
    private List<String> ingredients;
    private List<String> instructions;

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

    public List<NutritionalInformation> getNutritionalInformationUiModelList() {
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
}
