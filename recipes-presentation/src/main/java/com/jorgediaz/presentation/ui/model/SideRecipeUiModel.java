package com.jorgediaz.presentation.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class SideRecipeUiModel implements Parcelable {

    private final int servings;
    private final String style;
    private final String title;
    private final List<NutritionalInformationUiModel> nutritionalInformationList;
    private final List<String> ingredients;
    private final List<String> instructions;

    public SideRecipeUiModel(int servings, String style, String title, List<NutritionalInformationUiModel> nutritionalInformationList, List<String> ingredients, List<String> instructions) {
        this.servings = servings;
        this.style = style;
        this.title = title;
        this.nutritionalInformationList = nutritionalInformationList;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public int getServings() {
        return servings;
    }

    public String getStyle() {
        return style;
    }

    public String getTitle() {
        return title;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(servings);
        out.writeString(style);
        out.writeString(title);
        out.writeList(nutritionalInformationList);
        out.writeStringList(ingredients);
        out.writeStringList(instructions);
    }

    protected SideRecipeUiModel(Parcel in) {
        servings = in.readInt();
        style = in.readString();
        title = in.readString();

        nutritionalInformationList = new ArrayList<>();
        in.readList(nutritionalInformationList, NutritionalInformationUiModel.class.getClassLoader());

        ingredients = new ArrayList<>();
        in.readStringList(ingredients);

        instructions = new ArrayList<>();
        in.readStringList(instructions);
    }

    public static final Creator<SideRecipeUiModel> CREATOR = new Creator<SideRecipeUiModel>() {
        @Override
        public SideRecipeUiModel createFromParcel(Parcel in) {
            return new SideRecipeUiModel(in);
        }

        @Override
        public SideRecipeUiModel[] newArray(int size) {
            return new SideRecipeUiModel[size];
        }
    };

}
