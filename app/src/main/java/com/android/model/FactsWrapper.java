
package com.android.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class FactsWrapper {

    @SerializedName("rows")
    private List<Facts> mRows;
    @SerializedName("title")
    private String mTitle;

    public List<Facts> getRows() {
        return mRows;
    }

    public void setRows(List<Facts> rows) {
        mRows = rows;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

}
