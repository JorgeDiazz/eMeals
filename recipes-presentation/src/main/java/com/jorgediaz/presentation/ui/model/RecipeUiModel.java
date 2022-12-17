package com.jorgediaz.presentation.ui.model;


import android.os.Parcel;
import android.os.Parcelable;

public class RecipeUiModel implements Parcelable {

    private final int id;

    public RecipeUiModel(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
    }

    protected RecipeUiModel(Parcel in) {
        id = in.readInt();
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
