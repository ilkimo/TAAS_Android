package com.example.datahubapp.data.model;

import java.util.ArrayList;

public class NewTopic {

    private String id; //TODO this is user_id, change variable name (and correct backend and website because the JSON will change... damn)

    private String name;

    private String description;

    private ArrayList<DataInfoPair> nameType;

    private ArrayList<String> color;

    private Boolean shared;


    public Boolean getShared() {
        return shared;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getColor() {
        return color;
    }

    public ArrayList<DataInfoPair> getNameType() {
        return nameType;
    }

    public String getName() {
        return name;
    }
}
