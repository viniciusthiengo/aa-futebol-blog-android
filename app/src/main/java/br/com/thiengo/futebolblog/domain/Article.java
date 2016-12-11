package br.com.thiengo.futebolblog.domain;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by viniciusthiengo on 10/12/16.
 */

public class Article implements Parcelable {
    public static final String URL = "http://192.168.25.221:8888/futebol-blog/package/ctrl/CtrlAdmin.php";
    public static final String KEY = "article";

    private long id;
    private String title;
    private String content;
    private String imageUrl;
    private String summary;
    private int numViews;
    private int numComments;
    private String timeToRead;


    public Article(){}

    public Article( long id, String title, String content, String imageUrl, String summary, int numViews, int numComments, String timeToRead ){
        this.id = id;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.summary = summary;
        this.numViews = numViews;
        this.numComments = numComments;
        this.timeToRead = timeToRead;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getNumViews() {
        return numViews;
    }

    public void setNumViews(int numViews) {
        this.numViews = numViews;
    }

    public int getNumComments() {
        return numComments;
    }

    public void setNumComments(int numComments) {
        this.numComments = numComments;
    }

    public String getTimeToRead() {
        return timeToRead;
    }

    public void setTimeToRead(String timeToRead) {
        this.timeToRead = timeToRead;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeString(this.imageUrl);
        dest.writeString(this.summary);
        dest.writeInt(this.numViews);
        dest.writeInt(this.numComments);
        dest.writeString(this.timeToRead);
    }

    protected Article(Parcel in) {
        this.id = in.readLong();
        this.title = in.readString();
        this.content = in.readString();
        this.imageUrl = in.readString();
        this.summary = in.readString();
        this.numViews = in.readInt();
        this.numComments = in.readInt();
        this.timeToRead = in.readString();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel source) {
            return new Article(source);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    public void setFromJson(JSONObject json) throws JSONException {
        this.id = json.getInt("id");
        this.title = json.getString("title");
        this.content = json.getString("content");
        this.imageUrl = json.getString("imageUrl");
        this.summary = json.getString("summary");
        this.numViews = json.getInt("numViews");
        this.numComments = json.getInt("numComments");
        this.timeToRead = json.getString("timeToRead");
    }
}
