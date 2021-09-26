package com.example.relifems.gettersetter;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArticleData implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("newsSite")
    @Expose
    private String newsSite;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("publishedAt")
    @Expose
    private String publishedAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("featured")
    @Expose
    private boolean featured;
    @SerializedName("launches")
    @Expose
    private Object launches;
    @SerializedName("events")
    @Expose
    private Object events;

    public final static Creator<ArticleData> CREATOR = new Creator<ArticleData>() {
        @SuppressWarnings({"unchecked"})
        public ArticleData createFromParcel(Parcel in) {
            return new ArticleData(in);
        }

        public ArticleData[] newArray(int size) {
            return (new ArticleData[size]);
        }
    };

    protected ArticleData(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.url = ((String) in.readValue((String.class.getClassLoader())));
        this.imageUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.newsSite = ((String) in.readValue((String.class.getClassLoader())));
        this.summary = ((String) in.readValue((String.class.getClassLoader())));
        this.publishedAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
        this.featured = ((boolean) in.readValue((Boolean.class.getClassLoader())));
        this.launches = in.readValue((Object.class.getClassLoader()));
        this.events = in.readValue((Object.class.getClassLoader()));
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(title);
        dest.writeValue(url);
        dest.writeValue(imageUrl);
        dest.writeValue(newsSite);
        dest.writeValue(summary);
        dest.writeValue(publishedAt);
        dest.writeValue(updatedAt);
        dest.writeValue(featured);
        dest.writeValue(launches);
        dest.writeValue(events);
    }

    public int describeContents() {
        return 0;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getNewsSite() {
        return newsSite;
    }

    public String getSummary() {
        return summary;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public boolean isFeatured() {
        return featured;
    }

    public Object getLaunches() {
        return launches;
    }

    public Object getEvents() {
        return events;
    }

}