package com.example.relifems.gettersetter;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Breed implements Parcelable {

    @SerializedName("breeds")
    @Expose
    private List<GetSetBreeds> breeds = null;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("width")
    @Expose
    private int width;
    @SerializedName("height")
    @Expose
    private int height;

    public final static Parcelable.Creator<Breed> CREATOR = new Parcelable.Creator<Breed>() {
        @SuppressWarnings({"unchecked"})
        public Breed createFromParcel(Parcel in) {
            return new Breed(in);
        }

        public Breed[] newArray(int size) {
            return (new Breed[size]);
        }
    };

    protected Breed(Parcel in) {
        in.readList(this.breeds, (GetSetBreeds.class.getClassLoader()));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.url = ((String) in.readValue((String.class.getClassLoader())));
        this.width = ((int) in.readValue((Integer.class.getClassLoader())));
        this.height = ((int) in.readValue((Integer.class.getClassLoader())));
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(breeds);
        dest.writeValue(id);
        dest.writeValue(url);
        dest.writeValue(width);
        dest.writeValue(height);
    }

    public int describeContents() {
        return 0;
    }

    public List<GetSetBreeds> getBreeds() {
        return breeds;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
