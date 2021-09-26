package com.example.relifems.gettersetter;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetSetBreeds implements Parcelable {

    @SerializedName("weight")
    @Expose
    private Weight weight;
    @SerializedName("height")
    @Expose
    private Height height;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("breed_group")
    @Expose
    private String breedGroup;
    @SerializedName("life_span")
    @Expose
    private String lifeSpan;
    @SerializedName("temperament")
    @Expose
    private String temperament;

    public final static Parcelable.Creator<GetSetBreeds> CREATOR = new Parcelable.Creator<GetSetBreeds>() {
        @SuppressWarnings({"unchecked"})
        public GetSetBreeds createFromParcel(Parcel in) {
            return new GetSetBreeds(in);
        }

        public GetSetBreeds[] newArray(int size) {
            return (new GetSetBreeds[size]);
        }
    };

    protected GetSetBreeds(Parcel in) {
        this.weight = ((Weight) in.readValue((Weight.class.getClassLoader())));
        this.height = ((Height) in.readValue((Height.class.getClassLoader())));
        this.id = ((int) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.breedGroup = ((String) in.readValue((String.class.getClassLoader())));
        this.lifeSpan = ((String) in.readValue((String.class.getClassLoader())));
        this.temperament = ((String) in.readValue((String.class.getClassLoader())));
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(weight);
        dest.writeValue(height);
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(breedGroup);
        dest.writeValue(lifeSpan);
        dest.writeValue(temperament);
    }

    public int describeContents() {
        return 0;
    }

    public Weight getWeight() {
        return weight;
    }

    public Height getHeight() {
        return height;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBreedGroup() {
        return breedGroup;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public String getTemperament() {
        return temperament;
    }

}
