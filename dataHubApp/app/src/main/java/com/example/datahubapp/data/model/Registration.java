package com.example.datahubapp.data.model;


import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.datahubapp.data.model.SourceDataInterface;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.util.ArrayList;

public class Registration {
    @JsonProperty("id")
    private Long id;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonProperty("creationDate")
    private LocalDate creationDate;

    @JsonProperty("typeNameRegistration")
    private ArrayList<SourceDataInterface> typeNameRegistration;

    public Registration(){
        super();
    }

    @JsonCreator
    public Registration(@JsonProperty("id")Long id,
                        @JsonProperty("creationDate")LocalDate creationDate,
                        @JsonProperty("typeNameRegistration")ArrayList<SourceDataInterface> typeNameRegistration) {
        this.id = id;
        this.creationDate = creationDate;
        this.typeNameRegistration = typeNameRegistration;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Registration(ArrayList<SourceDataInterface> val){
        this.creationDate = LocalDate.now();
        this.typeNameRegistration = val;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public ArrayList<SourceDataInterface> getTypeNameRegistration() {
        return typeNameRegistration;
    }

    public void setTypeNameRegistration(ArrayList<SourceDataInterface> typeNameRegistration) {
        this.typeNameRegistration = typeNameRegistration;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "registration{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", typeNameRegistration=" + typeNameRegistration +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        boolean res = true;

        if((obj != null) && (obj.getClass().equals(this.getClass()))) {
            Registration castedObj = (Registration) obj;

            //check id
            if((id != null) && (castedObj.getId() != null)) {
                if(!id.equals(castedObj.getId())) {
                    res = false;
                }
            } else if(!((id == null) && (castedObj.getId() == null))) {
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

            //check typeNameRegistration
            if((typeNameRegistration != null) && (castedObj.getTypeNameRegistration() != null)) {
                if(!typeNameRegistration.equals(castedObj.getTypeNameRegistration())) {
                    res = false;
                }
            } else if(!((typeNameRegistration == null) && (castedObj.getTypeNameRegistration() == null))) {
                res = false;
            }
        } else {
            res = false;
        }

        return res;
    }
}
