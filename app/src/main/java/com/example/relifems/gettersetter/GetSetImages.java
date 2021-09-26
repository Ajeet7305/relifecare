package com.example.relifems.gettersetter;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetSetImages {

    @SerializedName("lists")
    @Expose
    private List<Breed> getBreeds = null;

    public final static Parcelable.Creator<GetSetImages> CREATOR = new Parcelable.Creator<GetSetImages>() {
        @SuppressWarnings({"unchecked"})
        public GetSetImages createFromParcel(Parcel in) {
            return new GetSetImages(in);
        }

        public GetSetImages[] newArray(int size) {
            return (new GetSetImages[size]);
        }
    };

    protected GetSetImages(Parcel in) {
        in.readList(this.getBreeds, (GetSetBreeds.class.getClassLoader()));
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(getBreeds);
    }

    public int describeContents() {
        return 0;
    }

    public List<Breed> getBreeds() {
        return getBreeds;
    }

}
