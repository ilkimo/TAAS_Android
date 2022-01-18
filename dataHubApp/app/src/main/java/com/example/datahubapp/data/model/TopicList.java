package com.example.datahubapp.data.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class TopicList {
    ArrayList<Topic> sharedTopicList;

    @JsonCreator
    public TopicList(@JsonProperty("sharedTopicList")ArrayList<Topic> sharedTopicList) {
        this.sharedTopicList = sharedTopicList;
    }

    public void setSharedTopicList(ArrayList<Topic> sharedTopicList) {
        this.sharedTopicList = sharedTopicList;
    }

    public ArrayList<Topic> getSharedTopicList() {
        return sharedTopicList;
    }
}