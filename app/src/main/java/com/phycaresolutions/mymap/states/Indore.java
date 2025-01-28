
package com.phycaresolutions.mymap.states;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class Indore {

    @SerializedName("Br.Name")
    @Expose
    private String brName;



    public Indore(String brName) {

        this.brName = brName;
    }

    public String getBrName() {
        return brName;
    }

    public void setBrName(String brName) {
        this.brName = brName;
    }

}
