package com.phycaresolutions.mymap.states;

import java.util.List;
import java.util.Map;

public class Location {
    private Map<String, List<Cites>> UP;
    private Map<String, List<Cites>> MP;

    public Map<String, List<Cites>> getUP() {
        return UP;
    }

    public void setUP(Map<String, List<Cites>> UP) {
        this.UP = UP;
    }

    public Map<String, List<Cites>> getMP() {
        return MP;
    }

    public void setMP(Map<String, List<Cites>> MP) {
        this.MP = MP;
    }
}
