package com.jorgediaz.presentation.ui.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class RecipeUiModel implements Parcelable {

    private final int id;
    private final String primaryPictureUrl;
    private final String primaryPictureUrlMedium;
    private final Double rating;
    private final String time;
    private final String servings;
    private final String style;
    private String title;
    private final List<NutritionalInformationUiModel> nutritionalInformationList;
    private final List<String> ingredients;
    private final List<String> instructions;
    private final SideRecipeUiModel sideRecipe;

    public RecipeUiModel(int id, String primaryPictureUrl, String primaryPictureUrlMedium, Double rating, String time, String servings, String style, String title, List<NutritionalInformationUiModel> nutritionalInformationList, List<String> ingredients, List<String> instructions, SideRecipeUiModel sideRecipe) {
        this.id = id;
        this.primaryPictureUrl = primaryPictureUrl;
        this.primaryPictureUrlMedium = primaryPictureUrlMedium;
        this.rating = rating;
        this.time = time;
        this.servings = servings;
        this.style = style;
        this.title = title;
        this.nutritionalInformationList = nutritionalInformationList;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.sideRecipe = sideRecipe;
    }

    public int getId() {
        return id;
    }

    public String getPrimaryPictureUrl() {
        return primaryPictureUrl;
    }

    public String getPrimaryPictureUrlMedium() {
        return primaryPictureUrlMedium;
    }

    public Double getRating() {
        return rating;
    }

    public String getTime() {
        return time;
    }

    public String getServings() {
        return servings;
    }


    public String getStyle() {
        return style;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<NutritionalInformationUiModel> getNutritionalInformationList() {
        return nutritionalInformationList;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public SideRecipeUiModel getSideRecipe() {
        return sideRecipe;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeString(primaryPictureUrl);
        out.writeString(primaryPictureUrlMedium);
        out.writeDouble(rating);
        out.writeString(time);
        out.writeString(servings);
        out.writeString(style);
        out.writeString(title);
        out.writeList(nutritionalInformationList);
        out.writeStringList(ingredients);
        out.writeStringList(instructions);
        out.writeParcelable(sideRecipe, flags);
    }

    protected RecipeUiModel(Parcel in) {
        id = in.readInt();
        primaryPictureUrl = in.readString();
        primaryPictureUrlMedium = in.readString();
        rating = in.readDouble();
        time = in.readString();
        servings = in.readString();
        style = in.readString();
        title = in.readString();

        nutritionalInformationList = new ArrayList<>();
        in.readList(nutritionalInformationList, NutritionalInformationUiModel.class.getClassLoader());

        ingredients = new ArrayList<>();
        in.readStringList(ingredients);

        instructions = new ArrayList<>();
        in.readStringList(instructions);

        sideRecipe = in.readParcelable(SideRecipeUiModel.class.getClassLoader());
    }

    public static final Creator<RecipeUiModel> CREATOR = new Creator<RecipeUiModel>() {
        @Override
        public RecipeUiModel createFromParcel(Parcel in) {
            return new RecipeUiModel(in);
        }

        @Override
        public RecipeUiModel[] newArray(int size) {
            return new RecipeUiModel[size];
        }
    };
}
