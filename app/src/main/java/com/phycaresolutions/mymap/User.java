package com.phycaresolutions.mymap;

import java.io.Serializable;

public class User implements Serializable {
    String name;
    String id;
    String branch;

    public User(String name, String id, String branch) {
        this.name = name;
        this.id = id;
        this.branch = branch;
    }
}
