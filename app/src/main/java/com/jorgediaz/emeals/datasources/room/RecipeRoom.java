package com.jorgediaz.emeals.datasources.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipes")
public class RecipeRoom {

    @PrimaryKey
    public int id;
    @ColumnInfo(name = "primary_picture_url")
    public String primaryPictureUrl;
    @ColumnInfo(name = "primary_picture_url_medium")
    public String primaryPictureUrlMedium;
    @ColumnInfo(name = "rating")
    public Double rating;
    @ColumnInfo(name = "time")
    public int time;
    @ColumnInfo(name = "servings")
    public int servings;
    @ColumnInfo(name = "style")
    public String style;
    @ColumnInfo(name = "title")
    public String title;
    @ColumnInfo(name = "nutritional_information")
    public String nutritionalInformation;
    @ColumnInfo(name = "ingredients")
    public String ingredients;
    @ColumnInfo(name = "instructions")
    public String instructions;

    @ColumnInfo(name = "side_servings")
    public int sideServings;
    @ColumnInfo(name = "side_style")
    public String sideStyle;
    @ColumnInfo(name = "side_title")
    public String sideTitle;
    @ColumnInfo(name = "side_nutritional_information")
    public String sideNutritionalInformation;
    @ColumnInfo(name = "side_ingredients")
    public String sideIngredients;
    @ColumnInfo(name = "side_instructions")
    public String sideInstructions;
}
