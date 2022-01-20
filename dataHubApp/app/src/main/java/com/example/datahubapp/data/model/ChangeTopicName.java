package com.example.datahubapp.data.model;

public class ChangeTopicName {
    private String id; //idUser
    private String name;
    private String newName;

    public ChangeTopicName(String id, String name, String newName) {
        this.id = id;
        this.name = name;
        this.newName = newName;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNewName() {
        return newName;
    }
}
