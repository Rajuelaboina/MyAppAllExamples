package com.phycaresolutions.mymap.model;

public class Db_Item  {
    String name;
    String password;
    byte[] inputData;

    public Db_Item(String name, String password, byte[] inputData) {
        this.name = name;
        this.password = password;
        this.inputData = inputData;
    }

    public Db_Item() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getInputData() {
        return inputData;
    }

    public void setInputData(byte[] inputData) {
        this.inputData = inputData;
    }
}
