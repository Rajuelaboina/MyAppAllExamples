
package com.phycaresolutions.mymap.states;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class Up {

    @SerializedName("Agra")
    @Expose
    private List<Agra> agra;
    @SerializedName("Bareilly")
    @Expose
    private List<Bareilly> bareilly;


    public Up(List<Agra> agra, List<Bareilly> bareilly) {
        this.agra = agra;
        this.bareilly = bareilly;
    }

    public List<Agra> getAgra() {
        return agra;
    }

    public void setAgra(List<Agra> agra) {
        this.agra = agra;
    }

    public List<Bareilly> getBareilly() {
        return bareilly;
    }

    public void setBareilly(List<Bareilly> bareilly) {
        this.bareilly = bareilly;
    }

}
