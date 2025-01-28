
package com.phycaresolutions.mymap.states;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class Cites {
    @SerializedName("UP")
    @Expose
    private List<Up> up;
    @SerializedName("MP")
    @Expose
    private List<Mp> mp;



    public List<Up> getUp() {
        return up;
    }

    public void setUp(List<Up> up) {
        this.up = up;
    }

    public List<Mp> getMp() {
        return mp;
    }
    public void setMp(List<Mp> mp) {
        this.mp = mp;
    }
}
