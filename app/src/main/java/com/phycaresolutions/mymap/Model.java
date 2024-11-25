package com.phycaresolutions.mymap;

public class Model {
    int type;
    int id;
    String text;
    public static final int TEXT_TYPE = 1;
    public static final int IMAGE_TYPE = 2;
    public static final int AUDIO_TYPE = 3;

    public Model(int textType, String s, int id) {
        this.type = textType;
        this.text = s;
        this.id = id;
    }
}
