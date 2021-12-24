package com.example.datahubapp.data.model;

/*
 * Classe cher sara il documento che viene salvato su mongo DB
 * abbiamo diversi dati
 * un id
 * un id relativo all'utente
 * e la lista dei topic
 */

import java.util.ArrayList;

public class UserData {
    private String id;

    private String idUser;

    private ArrayList<Topic> topicList;

    public UserData(String Userid){
        this.idUser = Userid;
        this.topicList = new ArrayList<>();
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


}

