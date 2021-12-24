package com.example.datahubapp.data.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.datahubapp.data.model.utilities.DataInfoPair;

import java.time.LocalDate;
import java.util.ArrayList;

public class Topic {
    private Long id;

    private String name;

    private String description;

    private LocalDate creationDate;//data di creazione

    private ArrayList<DataInfoPair> nameType;

    private ArrayList<String> color;

    private ArrayList<Registration> listRegistrazioni;//lista di future registrazioni

    private Long numberRecords;

    private Boolean shared;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Topic(String name, String description, ArrayList<String> colors, ArrayList<DataInfoPair> nameType, Boolean shared){
        this.name = name;
        this.description = description;
        this.creationDate = LocalDate.now();
        this.nameType = nameType;
        this.color = colors;
        listRegistrazioni = new ArrayList<>();
        this.numberRecords = Long.valueOf(0);
        this.shared = shared;
    }

    public void setListRegistrazioni(ArrayList<Registration> listRegistrazioni) {
        this.listRegistrazioni = listRegistrazioni;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setColor(ArrayList<String> color) {
        this.color = color;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNameType(ArrayList<DataInfoPair> nameType) {
        this.nameType = nameType;
    }

    public ArrayList<DataInfoPair> getNameType() {
        return nameType;
    }

    public ArrayList<String> getColor() {
        return color;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Registration> getListRegistrazioni() {
        return listRegistrazioni;
    }

    public String getName() {
        return name;
    }

    public Long getNumberRecords() {
        return numberRecords;
    }

    public void setNumberRecords(Long numberRecords) {
        this.numberRecords = numberRecords;
    }

    public Boolean getShared() {
        return shared;
    }

    public void setShared(Boolean shared) {
        this.shared = shared;
    }

    @Override
    public String toString() {
        return "topic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                ", nameType=" + nameType +
                ", color=" + color +
                ", listRegistrazioni=" + listRegistrazioni +
                ", numberRecords=" + numberRecords +
                ", shared=" + shared +
                '}';
    }


    //se ritorna true il nome per il topic é gia preso
    public static boolean exist(ArrayList<Topic> topic,String name){
        for(int a=0;a<topic.size();++a){
            if (topic.get(a).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

}
