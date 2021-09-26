package com.example.relifems.gettersetter;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Height implements Parcelable {

    @SerializedName("imperial")
    @Expose
    private String imperial;
    @SerializedName("metric")
    @Expose
    private String metric;

    public final static Parcelable.Creator<Height> CREATOR = new Parcelable.Creator<Height>() {
        @SuppressWarnings({"unchecked"})
        public Height createFromParcel(Parcel in) {
            return new Height(in);
        }

        public Height[] newArray(int size) {
            return (new Height[size]);
        }
    };

    protected Height(Parcel in) {
        this.imperial = ((String) in.readValue((String.class.getClassLoader())));
        this.metric = ((String) in.readValue((String.class.getClassLoader())));
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(imperial);
        dest.writeValue(metric);
    }

    public int describeContents() {
        return 0;
    }

    public String getImperial() {
        return imperial;
    }

    public String getMetric() {
        return metric;
    }

}
