package com.jorgediaz.presentation.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class NutritionalInformationUiModel implements Parcelable {

    private final String name;
    private final double value;
    private final String unit;

    public NutritionalInformationUiModel(String name, double value, String unit) {
        this.name = name;
        this.value = value;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    @NonNull
    @Override
    public String toString() {
        return name.substring(0, 3) + ".\n" + value + unit;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeDouble(value);
        out.writeString(unit);
    }

    protected NutritionalInformationUiModel(Parcel in) {
        name = in.readString();
        value = in.readDouble();
        unit = in.readString();
    }

    public static final Creator<NutritionalInformationUiModel> CREATOR = new Creator<NutritionalInformationUiModel>() {
        @Override
        public NutritionalInformationUiModel createFromParcel(Parcel in) {
            return new NutritionalInformationUiModel(in);
        }

        @Override
        public NutritionalInformationUiModel[] newArray(int size) {
            return new NutritionalInformationUiModel[size];
        }
    };
}
