package com.example.datahubapp.data.model;

import java.util.ArrayList;

public class NewRegistration {

    private String userId;

    private String topic;

    ArrayList<String> dataList;

    public NewRegistration(){
        dataList = new ArrayList<>();
    }


    public String getTopic() {
        return topic;
    }

    public ArrayList<String> getDataList() {
        return dataList;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "newRegistration{" +
                "userId='" + userId + '\'' +
                ", topic='" + topic + '\'' +
                ", dataList=" + dataList +
                '}';
    }
}
