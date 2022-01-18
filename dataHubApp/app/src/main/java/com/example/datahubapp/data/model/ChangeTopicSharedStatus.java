package com.example.datahubapp.data.model;

public class ChangeTopicSharedStatus {
    private String id; //idUser
    private String name; //name topic
    private String newName; //TODO remove me I'm useless (and correct backend)

    public ChangeTopicSharedStatus(String id, String name, String newName) {
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
