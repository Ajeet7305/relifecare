package com.example.relifems.gettersetter;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetSetArticle implements Parcelable {

    @SerializedName("lists")
    @Expose
    private List<ArticleData> articleData = null;

    public final static Creator<GetSetArticle> CREATOR = new Creator<GetSetArticle>() {
        @SuppressWarnings({"unchecked"})
        public GetSetArticle createFromParcel(Parcel in) {
            return new GetSetArticle(in);
        }

        public GetSetArticle[] newArray(int size) {
            return (new GetSetArticle[size]);
        }
    };

    protected GetSetArticle(Parcel in) {
        in.readList(this.articleData, (ArticleData.class.getClassLoader()));
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(articleData);
    }

    public int describeContents() {
        return 0;
    }

    public List<ArticleData> getArticleData() {
        return articleData;
    }

}