package com.phycaresolutions.mymap.pushnotifirebase;

import com.google.gson.annotations.SerializedName;


public class NotificationData {

    @SerializedName("detail")
    private String mDetail;

    @SerializedName("title")
    private String mTitle;

    public String getDetail() {
        return mDetail;
    }

    public void setDetail(String detail) {
        mDetail = detail;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

}
