
package com.phycaresolutions.mymap.states;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class Mp {

    @SerializedName("INDORE")
    @Expose
    private List<Indore> indore;
    @SerializedName("BHOPAL")
    @Expose
    private List<Bhopal> bhopal;



    public Mp(List<Indore> indore, List<Bhopal> bhopal) {

        this.indore = indore;
        this.bhopal = bhopal;
    }

    public List<Indore> getIndore() {
        return indore;
    }

    public void setIndore(List<Indore> indore) {
        this.indore = indore;
    }

    public List<Bhopal> getBhopal() {
        return bhopal;
    }

    public void setBhopal(List<Bhopal> bhopal) {
        this.bhopal = bhopal;
    }

}
