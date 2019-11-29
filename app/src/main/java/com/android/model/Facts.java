package com.android.model;

import com.google.gson.annotations.SerializedName;

public class Facts {

    @SerializedName("title")
    private String mTitle;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("imageHref")
    private String mImageHref;

    @SerializedName("pubDate")

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getImageHref() {
        return mImageHref;
    }

    public void setImageHref(String imageHref) {
        mImageHref = imageHref;
    }
}
