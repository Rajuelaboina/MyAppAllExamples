package com.phycaresolutions.mymap.userjetpack;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class Detail {

    @SerializedName("Provider_Name")
    @Expose
    private String providerName;
    @SerializedName("Photo")
    @Expose
    private String photo;

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}