package com.example.datahubapp.data.model;

/*
 * Classe cher sara il documento che viene salvato su mongo DB
 * abbiamo diversi dati
 * un id
 * un id relativo all'utente
 * e la lista dei topic
 */

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
public class UserData {
    private String id;

    private String idUser;

    private ArrayList<Topic> topicList;

    public UserData(String Userid) {
        this.idUser = Userid;
        this.topicList = new ArrayList<>();
    }

    private UserData() {}

    public static UserData emptyInstance() {
        return new UserData();
    }

    @JsonCreator
    public UserData(@JsonProperty("id")String id,
                    @JsonProperty("idUser")String idUser,
                    @JsonProperty("topicList")ArrayList<Topic> topic) {
        this.id = id;
        this.idUser = idUser;
        this.topicList = topic;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setTopicList(ArrayList<Topic> topicList) {
        this.topicList = topicList;
    }

    public String getId() {
        return id;
    }

    public String getIdUser() {
        return idUser;
    }

    public ArrayList<Topic> getTopicList() {
        return topicList;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id='" + id + '\'' +
                ", idUser=" + idUser +
                ", topicList =" + topicList +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        boolean res = true;

        if((obj != null) && (obj.getClass().equals(this.getClass()))) {
            UserData castedObj = (UserData) obj;

            //check id
            if((id != null) && (castedObj.getId() != null)) {
                if(!id.equals(castedObj.getId())) {
                    res = false;
                }
            } else if(!((id == null) && (castedObj.getId() == null))) {
                res = false;
            }

            //check idUser
            if((idUser != null) && (castedObj.getIdUser() != null)) {
                if(!idUser.equals((castedObj.getIdUser()))) {
                    res = false;
                }
            } else if(!((idUser == null) && (castedObj.getIdUser() == null))) {
                res = false;
            }

            //check topicList
            if((topicList != null) && (castedObj.getTopicList() != null)) {
                if(!topicList.equals(castedObj.getTopicList())) {
                    res = false;
                }
            } else if(!((topicList == null) && (castedObj.getTopicList() == null))) {
                res = false;
            }
        } else {
            res = false;
        }

        return res;
    }
}

