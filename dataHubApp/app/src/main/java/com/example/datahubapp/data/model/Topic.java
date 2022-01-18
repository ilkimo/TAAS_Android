package com.example.datahubapp.data.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.util.ArrayList;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
public class Topic {
    private Long id;

    private String name;

    private String description;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate creationDate;//data di creazione

    private ArrayList<DataInfoPair> nameType;

    private ArrayList<String> color;

    private ArrayList<Registration> listRegistrazioni; //lista di future registrazioni

    private Long numberRecords;

    private Boolean shared;

    public Topic clone() {
        return new Topic(
                id, name, description,
                creationDate, color, listRegistrazioni,
                nameType, numberRecords, shared
        );
    }

    @JsonCreator
    public Topic(@JsonProperty("id")Long id,
                 @JsonProperty("name")String name,
                 @JsonProperty("description")String description,
                 @JsonProperty("creationDate")LocalDate creationDate,
                 @JsonProperty("color")ArrayList<String> colors,
                 @JsonProperty("listRegistrazioni") ArrayList<Registration> listRegistrazioni,
                 @JsonProperty("nameType")ArrayList<DataInfoPair> nameType,
                 @JsonProperty("numberRecords")Long numberRecords,
                 @JsonProperty("shared")Boolean shared) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.nameType = nameType;
        this.color = colors;
        this.listRegistrazioni = listRegistrazioni;
        this.numberRecords = numberRecords;
        this.shared = shared;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Topic(String name, String description, ArrayList<String> colors, ArrayList<DataInfoPair> nameType, Boolean shared) {
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
    public static boolean exist(ArrayList<Topic> topic, String name){
        for(int a=0;a<topic.size();++a){
            if (topic.get(a).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        boolean res = true;

        if((obj != null) && (obj.getClass().equals(this.getClass()))) {
            Topic castedObj = (Topic) obj;

            //check id
            if((id != null) && (castedObj.getId() != null)) {
                if(!id.equals(castedObj.getId())) {
                    res = false;
                }
            } else if(!((id == null) && (castedObj.getId() == null))) {
                res = false;
            }

            //check name
            if((name != null) && (castedObj.getName()!= null)) {
                if(!name.equals(castedObj.getName())) {
                    res = false;
                }
            } else if(!((name == null) && (castedObj.getName() == null))) {
                res = false;
            }

            //check description
            if((description != null) && (castedObj.getDescription() != null)) {
                if(!description.equals(castedObj.getDescription())) {
                    res = false;
                }
            } else if(!((description == null) && (castedObj.getDescription() == null))) {
                res = false;
            }

            //check creationDate
            if((creationDate != null) && (castedObj.getCreationDate() != null)) {
                if(!creationDate.equals(castedObj.getCreationDate())) {
                    res = false;
                }
            } else if(!((creationDate == null) && (castedObj.getCreationDate() == null))) {
                res = false;
            }

            //check nameType
            if((nameType != null) && (castedObj.getNameType()!= null)) {
                if(!nameType.equals(castedObj.getNameType())) {
                    res = false;
                }
            } else if(!((nameType == null) && (castedObj.getNameType() == null))) {
                res = false;
            }

            //check color
            if((color != null) && (castedObj.getColor() != null)) {
                if(!color.equals(castedObj.getColor())) {
                    res = false;
                }
            } else if(!((color == null) && (castedObj.getColor() == null))) {
                res = false;
            }

            //check listRegistrazioni
            if((listRegistrazioni != null) && (castedObj.getListRegistrazioni()!= null)) {
                if(!listRegistrazioni.equals(castedObj.getListRegistrazioni())) {
                    res = false;
                }
            } else if(!((listRegistrazioni == null) && (castedObj.getListRegistrazioni() == null))) {
                res = false;
            }

            //check numberRecords
            if((numberRecords != null) && (castedObj.getNumberRecords() != null)) {
                if(!numberRecords.equals(castedObj.getNumberRecords())) {
                    res = false;
                }
            } else if(!((numberRecords == null) && (castedObj.getNumberRecords() == null))) {
                res = false;
            }

            //check shared
            if((shared != null) && (castedObj.getShared() != null)) {
                if(!shared.equals(castedObj.getShared())) {
                    res = false;
                }
            } else if(!((shared == null) && (castedObj.getShared() == null))) {
                res = false;
            }
        } else {
            res = false;
        }

        return res;
    }
}
