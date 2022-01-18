package com.example.datahubapp.data.model;

import java.util.ArrayList;

public class NewRegistration {

    private String userId;

    private String topic;

    private ArrayList<String> dataList;

    public NewRegistration(){
        dataList = new ArrayList<>();
    }

    public NewRegistration(String userId, String topicName, ArrayList<String> dataList) {
        this.userId = userId;
        topic = topicName;
        this.dataList = dataList;
    }

    public String getTopic() {
        return topic;
    }

    public ArrayList<String> getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList<String> dataList) {
        this.dataList = dataList;
    }

    public void addDataListElement(String elem) {
        dataList.add(elem);
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
