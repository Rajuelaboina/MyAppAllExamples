package com.phycaresolutions.mymap.slideNavigation;

public class UserDetails {
    String name;
    String mobile;
    String description;
    boolean isExpanded;

    public UserDetails(String name, String mobile, String description) {
        this.name = name;
        this.mobile = mobile;
        this.description = description;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
