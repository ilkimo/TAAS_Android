package com.example.datahubapp.data.model;


import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.datahubapp.data.model.SourceDataInterface;

import java.time.LocalDate;
import java.util.ArrayList;

public class Registration {
    private Long id;
    private LocalDate creationDate;
    private ArrayList<SourceDataInterface> typeNameRegistration;

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
}
