package com.phycaresolutions.mymap.model;

public class ItemClass {
    private int viewType;
    private String text;
    private int imageResource;
    private String text2;

    // Constructor for first layout
    public ItemClass(int viewType, String text) {
        this.viewType = viewType;
        this.text = text;
    }

    // Constructor for second layout
    public ItemClass(int viewType, int imageResource, String text) {
        this.viewType = viewType;
        this.imageResource = imageResource;
        this.text = text;
    }
    public ItemClass(int viewType, int imageResource, String text, String text2) {
        this.viewType = viewType;
        this.imageResource = imageResource;
        this.text = text;
        this.text2 = text2;
    }

    public int getViewType() {
        return viewType;
    }

    public String getText() {
        return text;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }
}
